package com.joey.core;

import com.alibaba.fastjson.JSONObject;
import com.joey.core.rabbit.service.MQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

//import com.joey.core.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitApplicationTests {

    public final int THREAD_NUM = 3;

    RestTemplate restTemplate = new RestTemplate();

//    @Autowired
//    OrderService orderService;

    @Autowired
    MQService mqService;


    @Test
    public void conCurrentTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8080/user/get/1", String.class);
                    System.out.println(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i] = thread;
            thread.start();
            countDownLatch.countDown();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void createOrder() throws Exception {
        String orderId = UUID.randomUUID().toString();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("orderId", orderId);
        orderInfo.put("userId", "joey");
        orderInfo.put("orderContent", "香蕉巴拉");
//        orderService.createOrder(orderInfo);
//        mqService.sendMsg(orderInfo);

    }


}
