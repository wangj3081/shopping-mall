package com.shopping.integral.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  积分服务实体
 * @author wangjian
 * @version 1.0
 * @see IntegralRecordEntity
 * @since JDK1.8
 */
@Data
//@TableName(value = "integral_record")
public class IntegralRecordEntity implements Serializable {
//    @TableId
    private String id;

    @ApiModelProperty(value = "店铺编码")
    private String storageNo;

    @ApiModelProperty(value = "店铺名")
    private String storageName;

    @ApiModelProperty(value = "订单编码")
    private String orderNo;

    @ApiModelProperty(value = "支付编码")
    private String payNo;

    @ApiModelProperty(value = "消费金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "积分")
    private Long integral;


}
