package com.demo.consumer.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/17 10:40
 */
@Data
@Table(name = "tb_order")
public class Order {
    @Id
    private Integer id;

    private Integer prodId;

    private Integer prodNum;

}
