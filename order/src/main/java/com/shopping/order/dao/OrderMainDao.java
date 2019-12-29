package com.shopping.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.order.entity.OrderMainEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单主表 DAO
 * @author wangjian
 * @version 1.0
 * @see OrderMainDao
 * @since JDK1.8
 */
@Mapper
public interface OrderMainDao extends BaseMapper<OrderMainEntity> {

}
