package com.demo.provider.model;

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
 * @Date 2022/5/17 10:50
 */
@Data
@Table(name = "tb_stock")
public class Stock {

    @Id
    private Integer prodId;

    private Integer currStock;
}
