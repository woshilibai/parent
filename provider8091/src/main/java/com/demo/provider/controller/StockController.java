package com.demo.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/7 10:42
 */
@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    @PostMapping("/decreaseStock")
    public String decreaseStock(String id){
      log.info("库存服务8091-->商品id{}减少库存成功", id);
      return "8091减少库存成功";
    }
}
