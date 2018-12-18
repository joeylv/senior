package com.joey.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.joey.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void createOrder(JSONObject orderInfo) throws Exception {
        String sql = "insert into table_order (order_id, user_id,order_content,create_time) values(?,?,?,now())";
        System.out.println(orderInfo);
        int count = jdbcTemplate.update(sql, orderInfo.getString("orderId"), orderInfo.getString("userId"), orderInfo.getString("orderContent"));
        if (count != 1) {
            throw new Exception("订单创建失败,原因[数据库操作失败]");
        }
        //记录MQ消息
        saveLocalMsg(orderInfo);

    }

    @Override
    public void saveLocalMsg(JSONObject orderInfo) throws Exception {
        String sql = "insert into tb_distributed_message (unique_id,msg_content,msg_status,create_time) values(?,?,?,now())";
        int count = jdbcTemplate.update(sql, orderInfo.getString("orderId"), orderInfo.toJSONString(), 0);
        if (count != 1) {
            throw new Exception("消息保存失败,原因[数据库操作失败]");
        }
    }
}
