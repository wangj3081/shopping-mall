package com.shopping.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderGoodsDto
 * @since JDK1.8
 */
@Data
@ApiModel(value = "订单商品")
public class OrderGoodsDto implements Serializable {

    @ApiModelProperty(value = "店铺编码")
    private String storeNo;
    @ApiModelProperty(value = "店铺名")
    private String storeName;
    @ApiModelProperty(value = "商品编码")
    private String sku;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "购买数量")
    @Min(value = 1, message = "购买数不能小于1")
    private Long changeNum;
    @ApiModelProperty(value = "商品总价")
    private BigDecimal amount;

}
