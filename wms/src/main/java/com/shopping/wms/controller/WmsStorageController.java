package com.shopping.wms.controller;

import com.google.protobuf.ServiceException;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import com.shopping.wms.service.WmsStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仓储服务控制器
 * @author wangjian
 * @version 1.0
 * @see WmsStorageController
 * @since JDK1.8
 */
@Api(value = "仓储服务处理")
@Slf4j
@RestController
@RequestMapping(value = "/wmsStorage")
public class WmsStorageController {

    @Resource
    private WmsStorageService wmsStorageService;

    @ApiOperation(value = "订单支付完成扣减库存，做拣货发货处理", httpMethod = "POST")
    @PostMapping(value = "/updateWmsStorageList")
    public Result<String> updateWmsStorageList(@RequestBody List<OrderGoodsDto> list) {
        try {
            Integer result = wmsStorageService.updateWmsStorageList(list);
            new ServiceException("仓储服务异常了。。。。");
            if (result != null && result > 0) {
                return new Result<String>().success("更新成功");
            }
        } catch (Exception e) {
            log.error("订单支付完成扣减库存异常:「{}」", e);
            new ServiceException("仓储服务异常了:" + e.getMessage());
        }
        return new Result<String>().error("-1", "更新失败");
    }

    @PostMapping(value = "/echo")
    public Result<String> echo(@RequestBody List<OrderGoodsDto> list) {
        System.out.println("*****************");
        return new Result<String>().success("【Echo】:result");
    }
}
