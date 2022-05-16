package com.demo.consumer.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @Description: 自定义feign拦截器
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 14:29
 */
@Slf4j
public class CustomFeignInterceptor implements RequestInterceptor {
    public void apply(RequestTemplate requestTemplate) {
        //写一些自己的业务逻辑 带上token 什么之类的
        log.info("执行openFeign自定义拦截器");
        String access_token = UUID.randomUUID().toString();
        requestTemplate.header("Authorization",access_token);//设置认证
    }
}
