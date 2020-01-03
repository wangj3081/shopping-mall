package com.shopping.inventory.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体类
 * @author wangjian
 * @version 1.0
 * @see InventoryGoodsDto
 * @since JDK1.8
 */
@Data
public class InventoryGoodsDto implements Serializable {

    private String id;
    @ApiModelProperty(value = "店铺编码")
    private String storeNo;
    @ApiModelProperty(value = "店铺名")
    private String storeName;
    @ApiModelProperty(value = "商品编码")
    private String sku;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;
    @ApiModelProperty(value = "库存数")
    private Long storageNum;
    @ApiModelProperty(value = "可用数")
    private Long availableNum;
    @ApiModelProperty(value = "待支付数")
    private Long waitPayNum;
    @ApiModelProperty(value = "已售卖数")
    private Long sellNum;
    @ApiModelProperty(value = "商品状态: 0 在架 1 下架")
    private Integer goodsStatus;
}
