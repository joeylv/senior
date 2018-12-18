package com.joey.core.rabbit.service;

import com.alibaba.fastjson.JSONObject;
import com.joey.core.service.DispatchService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderDispatchConsumer {
    private Logger logger = LoggerFactory.getLogger(OrderDispatchConsumer.class);

    @Autowired
    DispatchService dispatchService;

    @RabbitListener(queues = "orderDispatchQueue")
    public void messageConsumer(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            JSONObject orderInfo = JSONObject.parseObject(message);
            logger.warn("收到MQ消息::", orderInfo.toJSONString());
            Thread.sleep(5000L);
            String orderId = orderInfo.getString("orderId");
            dispatchService.dispatch(orderId);
            channel.basicAck(tag, false);
        } catch (Exception e) {
//            e.printStackTrace();
            // 异常情况 根据需要 重发/丢弃
            // 重发一定次数后 丢弃， 日志告警
            channel.basicNack(tag, false, false);
            //系统关键数据 永远有人工干预
        }
        // 如果不给回复, 等这个consumer断开后 mq-service会继续推送
    }

}
