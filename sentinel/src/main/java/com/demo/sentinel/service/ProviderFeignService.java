package com.demo.sentinel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 10:48
 */
@FeignClient(name = "provider" , fallback = ProviderFeignServiceImpl.class)
public interface ProviderFeignService {

    // @RequestParam必须，否则请求参数丢失
    @PostMapping("/stock/decreaseStock")
    String decreaseStock(@RequestParam(value = "id") String id);
}
