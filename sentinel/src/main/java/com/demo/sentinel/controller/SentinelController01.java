package com.demo.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 基于sentinel api实现限流
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/9 11:11
 */
@RestController
@RequestMapping("/sentinel")
@Slf4j
public class SentinelController01 {

    //  sentinel限流的资源名称
    public static final String RESOURCE_NAME="sentinel";

    /**
     * 定义限流规则
     *
     * spring的初始化方法
     */
    @PostConstruct
    private static void initFlowRules(){
        //流控规则
        List<FlowRule> rules = new ArrayList<FlowRule>();
        //流控
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护资源的阈值
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);
        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 基于sentinel API 实现限流
     * 把需要控制流量的代码用 Sentinel API SphU.entry("HelloWorld") 和 entry.exit() 包围起来即可
     */
    @GetMapping(value = "/code")
    public String sentinelTest01(){
        Entry entry = null;
        try {
            //sentinel针对资源进行限制
            entry = SphU.entry(RESOURCE_NAME);
            // 被保护的逻辑
            String str = "Sentinel api 接口正常";
            log.info("====" + str + "====");
            return str;
        } catch (BlockException e) {
            log.error("接口限流错误", e);
            //资源访问阻止，被限流或被降级
            //进行相应的处理操作
            return "Sentinel接口被流控了";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }

    }

    /**
     * 基于sentinel注解实现限流
     * value:定义流控资源
     * blockHandle:定义流控降级后的处理方法
     * fallback:接口异常时的兜底方法
     * @return
     */
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "blockHandlerForTest02", fallback = "fallbackForTest02")
    @GetMapping(value = "/annotation")
    public String sentinelTest02(){

        // 模拟异常
        int i = 10/0;

        String str = "Sentinel annotation 接口正常";
        log.info("====" + str + "====");
        return str;
    }

    /**
     * sentinelTest02流控降级后的处理方法
     * 注意：
     * 1、一定要是public
     * 2、返回值一定要和源方法（sentinelTest02）保证一致，包含源方法的参数
     * 3、可以在参数最后添加BlockException，可以区分是什么规则的处理方法
     * @param ex
     * @return
     */
    public String blockHandlerForTest02(BlockException ex){
        log.error("接口限流错误", ex);
        return "执行流控降级后的处理方法";
    }

    /**
     * sentinelTest02异常后的处理方法
     * 注意：
     * 1、一定要是public
     * 2、返回值一定要和源方法（sentinelTest02）保证一致，包含源方法的参数
     * 3、可以在参数最后添加Throwable，可以区分是什么异常
     * @param e
     * @return
     */
    public String fallbackForTest02(Throwable e){
        log.error("接口执行出现业务异常", e);
        return "接口执行出现业务异常";
    }


    /**
     * 基于sentinel dashboard进行流控配置（生产上需要将sentinel dashboard限流规则进行持久化到nacos或其他...）
     * [
     *     {
     *          "resource":"/sentinel/dashboard",
     *          "limitApp":"default",
     *          "grade":1,
     *          "count":1,
     *          "strategy":0,
     *          "controlBehavior":0,
     *          "clusterMode":false
     *     }
     * ]
     *
     * resource: 资源名称
     * limitApp: 来源应用
     * grade: 阈值类型,0表示线程,1表示QPS
     * count: 单机阈值
     * strategy: 流控模式,0表示直接,1表示关联,2表示链路
     * controlBehavior: 流控效果,0表示快速失败,1表示Warm Up,2表示排队等待
     * clusterMode: 是否集群
     *
     * @return
     * Blocked by Sentinel (flow limiting)
     */
    @GetMapping(value = "/dashboard")
    public String sentinelTest03(){
        String str = "Sentinel dashboard 接口正常";
        log.info("====" + str + "====");
        return str;
    }

}
