package com.shopping.controller;

import com.shopping.order.dto.OrderPayDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.service.OrderService;
import com.shopping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 商品售卖服务
 * @author wangjian
 * @version 1.0
 * @see ShoppingSellController
 * @since JDK1.8
 */
@Api(value = "商品售卖")
@Slf4j
@RestController
@RequestMapping(value = "shoppingSell")
public class ShoppingSellController {

    @Resource
    private OrderService orderService;


    @ApiOperation(value = "创建订单", httpMethod = "POST")
    @PostMapping(value = "createOrder")
    public Result<String> createOrder(@RequestBody OrderReq orderReq) {
        // 订单编码
        StopWatch stopWatch = new StopWatch("createOrder");
        stopWatch.start();
        orderReq.setCreateTime(new Date());
        Result<String> resultData = orderService.createOrderData(orderReq);
        stopWatch.stop();
        log.info("【创建订单】响应：{}", stopWatch.prettyPrint() );
        return resultData;
    }

    @ApiOperation(value = "获取订单编码", httpMethod = "POST")
    @PostMapping(value = "getOrderNo")
    public Result<String> getOrderNo() {
        Result<String> orderNoResult = orderService.getOrderNo();
        return orderNoResult;
    }

    @ApiOperation(value = "订单支付", httpMethod = "POST")
    @PostMapping(value = "orderPay")
    public Result<String> orderPay(@RequestBody  OrderPayDto orderPayDto) {
        StopWatch watch = new StopWatch("订单支付");
        watch.start();
        // 做支付处理获取支付编码
        Result<String> result = orderService.orderPay(orderPayDto);
        watch.stop();
        log.info("【订单支付】相应时长:{}", watch.prettyPrint());
        return result;
    }
}
