package com.shopping.inventory.service;

import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品库存接口服务
 * @author wangjian
 * @version 1.0
 * @see InventoryService
 * @since JDK1.8
 */
@Api(value = "库存服务")
@FeignClient(value = "mall-inventory")
public interface InventoryService {

    @ApiOperation(value = "更新商品库存存量列表", httpMethod = "POST")
    @PostMapping(value = "/inventory/updateStorageNumGoodsList")
    Result<String> updateStorageNumGoodsList(@RequestBody List<OrderGoodsDto> orderGoodsDtoList);

    @ApiOperation(value = "更新商品库存存量列表", httpMethod = "POST")
    @PostMapping(value = "/inventory/updateAvailableNumGoodsList")
    Result<String> updateAvailableNumGoodsList(@RequestBody List<OrderGoodsDto> orderGoodsDtoList);
}
