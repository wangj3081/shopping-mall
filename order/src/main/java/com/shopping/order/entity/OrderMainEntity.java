package com.shopping.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 * @author wangjian
 * @version 1.0
 * @see OrderMainEntity
 * @since JDK1.8
 */
@Data
@TableName(value = "order_main")
public class OrderMainEntity implements Serializable {

    @TableId
    private String id;
    @ApiModelProperty(value = "订单编码")
    private String orderNo;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty(value = "订单创建时间")
    private Date createDate;
    @ApiModelProperty(value = "收件人")
    private String receiptPeople;
    @ApiModelProperty(value = "收件地址")
    private String receiptAddress;
    @ApiModelProperty(value = "订单状态: 0 待支付 1 已支付 2 已取消")
    private Integer orderStatus;
    @ApiModelProperty(value = "支付编码")
    private String payNo;
}
