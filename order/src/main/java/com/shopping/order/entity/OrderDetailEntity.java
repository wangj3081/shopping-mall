package com.shopping.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细表
 * @author wangjian
 * @version 1.0
 * @see OrderDetailEntity
 * @since JDK1.8
 */
@Data
@TableName(value = "order_detail")
public class OrderDetailEntity implements Serializable {

    @TableId
    private String id;
    @ApiModelProperty(value = "订单编码")
    private String orderNo;
    @ApiModelProperty(value = "店铺编码")
    private String storeNo;
    @ApiModelProperty(value = "店铺名")
    private String storeName;
    @ApiModelProperty(value = "商品编码")
    private String sku;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "数量")
    private Long changeNum;
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;
    @ApiModelProperty(value = "订单状态: 0 待支付 1 已支付 2 已取消")
    private Integer orderStatus;
}
