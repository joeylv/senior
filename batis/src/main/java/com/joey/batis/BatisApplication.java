package com.joey.batis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo"})
@MapperScan("com.demo.mapper")
public class BatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatisApplication.class, args);
    }
}
