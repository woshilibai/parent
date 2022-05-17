package com.demo.consumer.controller;

import com.alibaba.nacos.common.utils.RandomUtils;
import com.demo.consumer.mapper.OrderMapper;
import com.demo.consumer.model.Order;
import com.demo.consumer.service.ProviderFeignService;
import io.seata.spring.annotation.GlobalTransactional;
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
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    String providerServiceName = "provider";
    String providerServiceUrl = "http://{0}/stock/decreaseStock";

    @Autowired
    ProviderFeignService providerFeignService;

    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/add/{id}")
    public String addOrder(@PathVariable String id){
        //  restTemplate方式消费微服务
//        String result = restTemplate.getForObject(providerServiceUrl, String.class, providerServiceName);
        //  feign方式消费微服务
        String result = providerFeignService.decreaseStock(id);

        log.info("订单服务请求库存服务，返回信息-->{}", result);
        log.info("订单服务-->下单成功");

        return "下单成功";
    }

    /**
     * 演示skywalking的性能监控
     */
    @GetMapping("/list")
    public void list(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分布式事务测试，此时是TM角色
     * @param id  商品id
     * @param num  商品数量
     * @return
     */
    @GlobalTransactional    //  该注解开启了分布式事务
    @GetMapping("/save/{id}/{num}")
    public void addOrder(@PathVariable Integer id, @PathVariable Integer num){

        //  本地保存订单入库
        Order order = new Order();
        order.setId(RandomUtils.nextInt(100,10000));
        order.setProdId(id);
        order.setProdNum(num);
        orderMapper.insert(order);

        //  调用微服务stock，扣减库存
        providerFeignService.updateStock(id, num);

    }
}
