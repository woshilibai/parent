package com.demo.sentinel.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description: 定义Feign接口实现类（容错类），用于服务降级
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/9 14:42
 */
@Component
public class ProviderFeignServiceImpl implements ProviderFeignService {
    public String decreaseStock(String id) {
        return "/stock/decreaseStock 触发服务降级了，id=" + id;
    }
}
