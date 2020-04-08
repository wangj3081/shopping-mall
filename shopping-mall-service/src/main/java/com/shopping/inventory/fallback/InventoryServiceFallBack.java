package com.shopping.inventory.fallback;

import com.shopping.inventory.service.InventoryService;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName InventoryServiceFallBack
 * @Description 熔断实现类
 * @Author wangjian
 * @Date 2020/4/8 5:12 下午
 * @Version 1.0
 **/
@Component
public class InventoryServiceFallBack implements InventoryService {
    @Override
    public Result<String> updateStorageNumGoodsList(List<OrderGoodsDto> orderGoodsDtoList) {
        return new Result<String>().systemError();
    }

    @Override
    public Result<String> updateAvailableNumGoodsList(List<OrderGoodsDto> orderGoodsDtoList) {
        return new Result<String>().systemError();
    }
}
