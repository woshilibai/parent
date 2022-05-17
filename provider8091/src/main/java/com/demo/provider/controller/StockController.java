package com.demo.provider.controller;

import com.demo.provider.mapper.StockMapper;
import com.demo.provider.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    StockMapper stockMapper;

    @PostMapping("/decreaseStock")
    public String decreaseStock(String id){
      log.info("库存服务8091-->商品id{}减少库存成功", id);
      return "8091减少库存成功";
    }

    /**
     * 分布式事务测试，此时是RM角色
     * @param id  商品id
     * @param num  商品数量
     * @return
     */
    @PostMapping("/updateStock")
    public void updateStock(Integer id, Integer num){
        Stock stock = new Stock();
        stock.setProdId(id);
        stock = stockMapper.selectOne(stock);
        log.info("商品id：{}，下单前剩余库存为：{}",id, stock.getCurrStock());
        stock.setCurrStock(stock.getCurrStock()-num);
        log.info("商品id：{}，下单前剩余库存为：{}",id, stock.getCurrStock());
        stockMapper.updateByPrimaryKey(stock);
        //  模拟异常
        int i=10/0;
    }
}
