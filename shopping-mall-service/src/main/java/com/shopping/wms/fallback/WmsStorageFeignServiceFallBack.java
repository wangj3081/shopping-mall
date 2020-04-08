package com.shopping.wms.fallback;

import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import com.shopping.wms.service.WmsStorageFeignService;

import java.util.List;

/**
 * @ClassName WmsStorageFeignServiceFallBack
 * @Description 熔断方法实现
 * @Author wangjian
 * @Date 2020/4/8 5:14 下午
 * @Version 1.0
 **/
public class WmsStorageFeignServiceFallBack implements WmsStorageFeignService {
    @Override
    public Result<String> updateWmsStorageList(List<OrderGoodsDto> list) {
        return new Result<String>().systemError();
    }

    @Override
    public Result<String> echo(List<OrderGoodsDto> list) {
        return new Result<String>().systemError();
    }
}
