package com.shopping.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderReq
 * @since JDK1.8
 */
@Data
public class OrderReq implements Serializable {

    @ApiModelProperty(value = "要购买的商品与对应商店")
    private List<OrderGoodsDto> goodsDtoList;
    @ApiModelProperty(value = "订单编码")
    private String orderNo;
    @ApiModelProperty(value = "收件人")
    private String receiptPeople;
    @ApiModelProperty(value = "收件地址")
    private String receiptAddress;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
