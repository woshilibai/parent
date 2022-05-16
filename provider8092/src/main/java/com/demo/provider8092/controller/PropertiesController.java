package com.demo.provider8092.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 演示获取nacos配置中心配置参数
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 15:03
 */
@RefreshScope   //  配置参数动态刷新
@RestController
@RequestMapping("/prop")
@Slf4j
public class PropertiesController {

    /**
     * nacos作为配置中心时，读取配置文件的规则：${prefix}-${spring.profile.active}.${file-extension}
     * prefix 表示服务名
     * spring.profile.active 表示环境
     * file-extension 表示配置文件格式
     */
    @Value("${nacos.config.provider}")
    String propValue;

    @GetMapping("/get")
    public String getProp(){
        log.info("读取nacos配置中心配置参数示例：{}", propValue);
        return propValue;
    }
}
