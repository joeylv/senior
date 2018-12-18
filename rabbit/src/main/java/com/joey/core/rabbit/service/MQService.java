package com.joey.core.rabbit.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional(noRollbackFor = Exception.class)
public class MQService {
    Logger logger = LoggerFactory.getLogger(MQService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setup() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // ack 为true，代表MQ已准确收到消息
            if (!ack) {
                return;
            }
            try {
                // 修改本地消息 状态为"已发送" 删除 修改状态
                String sql = "update tb_distributed_message set msg_status = 1 where unique_id=?";
                int count = jdbcTemplate.update(sql, new String[]{correlationData.getId()});

                if (count != 1) {
                    logger.warn("警告：本地消息状态修改不成功！");
                }
            } catch (Exception e) {
                logger.warn("警告：修改本地消息状态出现异常！");
            }
        });
    }

    public void sendMsg(JSONObject msgInfo) throws Exception {

        rabbitTemplate.convertAndSend("createOrderExchange", "", msgInfo.toJSONString(), new CorrelationData(msgInfo.getString("orderId")));

    }

}
