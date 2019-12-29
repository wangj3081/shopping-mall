package com.shopping.wms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * wms 实体
 * @author wangjian
 * @version 1.0
 * @see WmsStorageEntity
 * @since JDK1.8
 */
@Data
@TableName(value = "wms_storage")
public class WmsStorageEntity implements Serializable {

    @TableId
    private String id;
    @ApiModelProperty(value = "店铺编码")
    private String storageNo;
    @ApiModelProperty(value = "店铺名")
    private String storageName;
    @ApiModelProperty(value = "商品编码")
    private String sku;
    @ApiModelProperty(value = "可用数量")
    private Long availableNum;
    @ApiModelProperty(value = "仓库存量")
    private Long storageNum;
    @ApiModelProperty(value = "待出库")
    private Long waitOutbount;
    @ApiModelProperty(value = "已出库")
    private Long outbount;
}
