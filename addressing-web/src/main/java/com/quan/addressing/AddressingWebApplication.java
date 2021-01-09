package com.quan.addressing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.quan.addressing.dao")
public class AddressingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressingWebApplication.class, args);
        System.out.println("quanquan8888888811111");
        System.out.println("quanquan88888888-2");
    }

}