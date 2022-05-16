package com.demo.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 10:48
 */
@Configuration
public class Config {

    @Bean
    @LoadBalanced   //  开启客户端负载均衡支持， 默认轮询
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
