package com.joey.queuemode.routing;

import com.joey.queuemode.connection.ConnectionUtil;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer2 {

    private final static String QUEUE_NAME = "routing_queue2";
    private final static String EXCHANGE_NAME = "routing_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //申明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //队列绑定交换机，指定路由routingKey
        //结束路由routingKey为error的消息
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");

        //同一时刻服务器只发送1条消息给消费者（能者多劳，消费消息快的，会消费更多的消息）
        //保证在接收端一个消息没有处理完时不会接收另一个消息，即消费者端发送了ack后才会接收下一个消息。
        //在这种情况下生产者端会尝试把消息发送给下一个空闲的消费者。
        channel.basicQos(1);

        //声明消费者
        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("路由模式 消费者2 消费消息：" + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer2);
    }
}
