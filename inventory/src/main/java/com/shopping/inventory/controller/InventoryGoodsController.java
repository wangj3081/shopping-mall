package com.shopping.inventory.controller;

import com.google.protobuf.ServiceException;
import com.shopping.inventory.entity.InventoryGoodsEntity;
import com.shopping.inventory.service.InventoryGoodsService;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺库存控制层
 * @author wangjian
 * @version 1.0
 * @see InventoryGoodsController
 * @since JDK1.8
 */
@Api(value = "店铺库存 API")
@Slf4j
@RestController
@RequestMapping(value = "/inventory")
public class InventoryGoodsController {

    @Resource
    private InventoryGoodsService goodsService;


    @GetMapping(value = "/insertGoodsListVal")
    public Integer insertGoodsListVal(List<InventoryGoodsEntity> list) {
        return goodsService.insertInventoryGoodsList(list);
    }

    @ApiOperation(value = "更新商品库存存量列表", httpMethod = "POST")
    @PostMapping(value = "/updateStorageNumGoodsList")
    public Result<String> updateStorageNumGoodsList(@RequestBody List<OrderGoodsDto> orderGoodsDtoList) {
        try {
            goodsService.updateStorageNumListByStorageNo(orderGoodsDtoList);
            return new Result<String>().success("SUCCESS");
        } catch (Exception e) {
            log.error("更新库存存量异常:「{}」", e);
        }
        return new Result<String>().error("-1", "更新库存存量异常");
    }

    @ApiOperation(value = "更新商品库存存量列表", httpMethod = "POST")
    @PostMapping(value = "/updateAvailableNumGoodsList")
    public Result<String> updateAvailableNumGoodsList(@RequestBody List<OrderGoodsDto> orderGoodsDtoList) {
        try {
            goodsService.updateAvailableNumListByStorageNo(orderGoodsDtoList);
            return new Result<String>().success("SUCCESS");
        } catch (Exception e) {
            log.error("更新库存可用异常:「{}」", e);
            new ServiceException("库存异常了");
        }
        return new Result<String>().error("-1", "更新库存可用异常");
    }

}
