package com.shopping.order.fallback;

import com.shopping.order.dto.OrderPayDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.service.OrderService;
import com.shopping.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderServiceFallBack
 * @Description 熔断方法实现
 * @Author wangjian
 * @Date 2020/4/8 2:00 下午
 * @Version 1.0
 **/
@Component
@Slf4j
public class OrderServiceFallBack implements OrderService {

    @Override
    public Result<String> getOrderNo() {
        return new Result().systemError();
    }

    @Override
    public Result<String> createOrderData(OrderReq orderReq) {
        return new Result().systemError();
    }

    @Override
    public Result<String> orderPay(OrderPayDto orderPayDto) {
        return new Result().systemError();
    }
}
