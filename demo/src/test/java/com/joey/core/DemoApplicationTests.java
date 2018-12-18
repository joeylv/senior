package com.joey.core;

import com.alibaba.fastjson.JSONObject;
import com.joey.core.rabbit.service.MQService;
import com.joey.core.rabbit.service.MessageProducer;
import com.joey.core.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource("classpath:application.yml")
public class DemoApplicationTests {

    @Autowired
    MQService mqService;

    @Autowired
    OrderService orderService;

    @Autowired
    MessageProducer messageProducer;


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() throws Exception {
        for (int i = 0; i < 30000; i++) {
            JSONObject orderInfo = new JSONObject();
            orderInfo.put("orderId", 1);
            orderInfo.put("userId", 1);
//        orderService.createOrder(orderInfo);

//        JSONObject orderInfo = new JSONObject();
//        orderInfo.put("orderId", 1);
//            mqService.sendMsg(orderInfo);
            messageProducer.sendMessage(orderInfo);
        }
    }

    @Test
    public void amqpTest() {
        for (int i = 0; i < 30000; i++) {

            rabbitTemplate.convertAndSend("boot", " from RabbitMQ!");
        }

    }


}

