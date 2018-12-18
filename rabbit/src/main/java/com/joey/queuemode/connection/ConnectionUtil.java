package com.joey.queuemode.connection;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接池
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //连接地址
        connectionFactory.setHost("192.168.12.170");
        //连接端口
        connectionFactory.setPort(5672);
        //用户名
        connectionFactory.setUsername("admin");
        //密码
        connectionFactory.setPassword("admin");
        //通过连接工厂获取连接
        Connection connection = connectionFactory.newConnection();
        //返回连接
        return connection;
    }
}
