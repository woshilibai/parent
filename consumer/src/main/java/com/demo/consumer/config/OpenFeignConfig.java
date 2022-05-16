package com.demo.consumer.config;

import com.demo.consumer.interceptor.CustomFeignInterceptor;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 全局配置：当使用@Configuration 会将配置作用所有的服务提供方
 * 局部配置：如果只想针对某一个服务进行配置，就不要加@Configuration
 */
@Configuration
public class OpenFeignConfig {

    /**
     * feign 日志打印配置
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    /**
     * feign 超时时间配置
     * @return
     */
//    @Bean
//    public Request.Options options(){
//        return new Request.Options(10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true);
//    }

    /**
     * 定义feign拦截器 全局的，如果需要局部的，在配置文件指定
     * @return
     */
    @Bean
    public CustomFeignInterceptor customFeignInterceptor(){
        return new CustomFeignInterceptor();
    }
}
