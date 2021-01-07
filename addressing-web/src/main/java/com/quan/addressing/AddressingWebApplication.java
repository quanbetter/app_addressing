package com.quan.addressing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.quan.addressing.dao")
public class AddressingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressingWebApplication.class, args);
    }

}
li