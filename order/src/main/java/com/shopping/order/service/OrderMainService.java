package com.shopping.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.order.dto.OrderPayDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.entity.OrderMainEntity;

import java.util.List;

/**
 * 订单主服务方法
 * @author wangjian
 * @version 1.0
 * @see OrderMainService
 * @since JDK1.8
 */
public interface OrderMainService extends IService<OrderMainEntity> {

    /**
     * 批量插入订单数据
     * @param listValue
     */
    Integer insertOrderMainList(List<OrderMainEntity> listValue);

    /**
     * 创建订单
     * @param orderReq
     * @return
     */
    String createOrder(OrderReq orderReq);

    /**
     * 获取订单编码
     * @return
     */
    String getOrderNo();

    /**
     * 获取订单支付编码
     * @return
     */
    String getPayNo();

    /**
     * 订单支付处理
     * @param orderNo 订单号
     * @param orderPay 订单支付编码
     * @return
     */
    String orderPay(String orderNo, String orderPay);
}
