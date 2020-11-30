package com.quan.appaddressingall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.quan.appaddressingall.dao")
public class AppAddressingAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppAddressingAllApplication.class, args);
    }

}
