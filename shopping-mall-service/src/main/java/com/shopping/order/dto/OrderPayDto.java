package com.shopping.order.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单支付入参
 * @author wangjian
 * @version 1.0
 * @see OrderPayDto
 * @since JDK1.8
 */
@Data
public class OrderPayDto {

    @NotNull(message = "订单编码不能为空")
    private String orderNo;
    @NotNull(message = "订单金额不能为空")
    private BigDecimal amount;
}
