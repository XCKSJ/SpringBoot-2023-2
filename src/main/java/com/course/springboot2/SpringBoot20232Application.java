package com.course.springboot2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.course.springboot2.mapper")
public class SpringBoot20232Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot20232Application.class, args);
    }
}
