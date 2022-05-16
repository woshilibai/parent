package com.demo.sentinel.controller;

import com.demo.sentinel.service.ProviderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 10:13
 */
@RestController
@RequestMapping("/sentinel")
@Slf4j
public class OrderController {

    @Autowired
    ProviderFeignService providerFeignService;

    @GetMapping("/addOrder/{id}")
    public String addOrder(@PathVariable String id){

        //  feign方式消费微服务
        String result = providerFeignService.decreaseStock(id);

        log.info("订单服务请求库存服务，返回信息-->{}", result);
        log.info("订单服务-->下单成功");

        return result;
    }
}
