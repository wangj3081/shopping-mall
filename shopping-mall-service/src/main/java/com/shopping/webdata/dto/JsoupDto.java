package com.shopping.webdata.dto;

import com.shopping.inventory.dto.InventoryGoodsDto;
import com.shopping.wms.dto.WmsStorageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see JsoupDto
 * @since JDK1.8
 */
@Data
public class JsoupDto implements Serializable {

    @ApiModelProperty(value = "商品库存集合")
    private List<InventoryGoodsDto> inventoryGoodsDtoList;
    @ApiModelProperty(value = "仓库库存集合")
    private List<WmsStorageDto> wmsStorageDtoList;

}
