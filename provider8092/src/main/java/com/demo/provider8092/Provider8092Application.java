package com.demo.provider8092;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.demo.provider8092.mapper")
public class Provider8092Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider8092Application.class, args);
    }

}
