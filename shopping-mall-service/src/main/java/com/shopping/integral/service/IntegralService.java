package com.shopping.integral.service;

import com.shopping.integral.dto.IntegralDto;
import com.shopping.inventory.fallback.InventoryServiceFallBack;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see IntegralService
 * @since JDK1.8
 */
@FeignClient(value = "mall-integral", url = "http://localhost:9501", fallback = InventoryServiceFallBack.class)
public interface IntegralService {


    /**
     * 根据店铺编码获取积分列表
     */
    @RequestMapping(value = "/integral/queryListByStorageNo", method = RequestMethod.GET)
    List<IntegralDto> queryListByStorageNo(@RequestParam(value = "storageNo") String storageNo);

    /**
     * echo 方法
     * @param message
     * @return
     */
    @RequestMapping(value = "/integral/echo", method = RequestMethod.GET)
    String echo(@RequestParam(value = "message") String message);
}
