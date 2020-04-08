package com.shopping.order.service;

import com.shopping.order.dto.OrderPayDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.fallback.OrderServiceFallBack;
import com.shopping.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderService
 * @since JDK1.8
 */
@FeignClient(value = "mall-order", url = "http://localhost:9501", fallback = OrderServiceFallBack.class)
public interface OrderService {
     /**
     * 获取订单编码服务
     * @return
     */
    @ApiOperation(value = "获取订单编码")
    @PostMapping(value = "/order/getOrderNo")
    Result<String> getOrderNo();

    /**
     * 创建订单数据
     * @param orderReq
     * @return
     */
    @ApiOperation(value = "创建订单数据")
    @PostMapping(value = "/order/createOrder")
    Result<String> createOrderData(@RequestBody OrderReq orderReq);

    /**
     * 订单支付
     * @param orderPayDto
     * @return
     */
    @ApiOperation(value = "订单支付", httpMethod = "POST")
    @PostMapping(value = "/order/orderPay")
    Result<String> orderPay(@RequestBody OrderPayDto orderPayDto);
}
