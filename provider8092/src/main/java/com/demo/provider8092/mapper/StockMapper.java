package com.demo.provider8092.mapper;

import com.demo.provider8092.model.Stock;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description: todo
 * @Author tianwl
 * @Company 安徽中科美络信息技术有限公司
 * @Email tianwl@izkml.com
 * @Date 2022/5/17 10:44
 */
public interface StockMapper extends Mapper<Stock>, MySqlMapper<Stock> {
}
