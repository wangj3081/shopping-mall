package com.shopping.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.order.entity.OrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单详情 DAO
 * @author wangjian
 * @version 1.0
 * @see OrderDetailDao
 * @since JDK1.8
 */
@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetailEntity> {
}
