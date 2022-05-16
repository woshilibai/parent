package com.demo.skywalkingalarm1004.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.oap.server.core.alarm.AlarmMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 利用钩子webhooks实现自定义skywalking告警
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/13 15:42
 */
@RestController
@Slf4j
@RequestMapping("/alarm")
public class AlarmController {
    @PostMapping("/receive")
    public void receive(@RequestBody List<AlarmMessage> list){
        //  这里仅仅打印json，可以根据业务需要实现发送邮件、微信、钉钉、打电话
        log.info("----------------------");
        log.info("收到来自skywalking的监控告警信息：{}" ,new Gson().toJson(list));
    }
}
