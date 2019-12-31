package com.shopping.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shopping.integral.service.IntegralService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see InventoryGoodsEntity
 * @since JDK1.8
 */
@Data
@TableName(value = "inventory_goods")
public class InventoryGoodsEntity implements Serializable {

    @TableId
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
