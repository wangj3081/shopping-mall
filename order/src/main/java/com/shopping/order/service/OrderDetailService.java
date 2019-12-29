package com.shopping.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.order.entity.OrderDetailEntity;

import java.util.List;

/**
 *  订单服务服务方法
 * @author wangjian
 * @version 1.0
 * @see OrderDetailService
 * @since JDK1.8
 */
public interface OrderDetailService extends IService<OrderDetailEntity> {

    /**
     * 批量插入详情
     * @param detailList
     * @return
     */
    Integer insertOrderDetailList(List<OrderDetailEntity> detailList);
}
