package com.shopping.wms.service;

import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 仓库服务接口
 * @author wangjian
 * @version 1.0
 * @see WmsStorageFeignService
 * @since JDK1.8
 */
@Api(value = "仓储服务")
@FeignClient(value = "mall-wms")
public interface WmsStorageFeignService {

    /**
     * 订单支付完成扣减库存，做拣货发货处理，更新仓储库存数据，做发货处理
     * @param list
     * @return
     */
    @ApiOperation(value = "扣减库存", httpMethod = "POST")
    @PostMapping(value = "/wmsStorage/updateWmsStorageList")
    Result<String> updateWmsStorageList(@RequestBody List<OrderGoodsDto> list);

    @ApiOperation(value = "扣减库存", httpMethod = "POST")
    @PostMapping(value = "/wmsStorage/echo")
    Result<String> echo(@RequestBody List<OrderGoodsDto> list);
}
