package com.joey.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitApplicationTests {

    public final int THREAD_NUM = 3;

    RestTemplate restTemplate = new RestTemplate();


    @Test
    public void conCurrentTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8080/api/", String.class);
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


}
