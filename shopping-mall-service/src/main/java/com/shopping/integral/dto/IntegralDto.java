package com.shopping.integral.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see IntegralDto
 * @since JDK1.8
 */
@Data
@ApiModel(value = "积分服务模型")
public class IntegralDto implements Serializable {

    @ApiModelProperty(value = "店铺编码")
    private String storageNo;

    @ApiModelProperty(value = "店铺名")
    private String storageName;

    @ApiModelProperty(value = "订单编码")
    private String orderNo;

    @ApiModelProperty(value = "消费金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "积分")
    private Long integral;
}
