package com.demo.consumer.service;

import com.demo.consumer.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 10:48
 */
@FeignClient(name = "provider" /*, configuration = OpenFeignConfig.class*/) //   configuration = OpenFeignConfig.class表示局部配置
public interface ProviderFeignService {

    // @RequestParam必须，否则请求参数丢失
    @PostMapping("/stock/decreaseStock")
    String decreaseStock(@RequestParam(value = "id") String id);

    @PostMapping("/stock/updateStock")
    void updateStock(@RequestParam(value = "id") Integer id, @RequestParam(value = "num") Integer num);
}
