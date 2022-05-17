package com.demo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.demo.provider.mapper")
public class Provider8091Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider8091Application.class, args);
    }

}
