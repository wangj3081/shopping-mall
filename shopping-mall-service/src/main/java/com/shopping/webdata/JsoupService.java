package com.shopping.webdata;

import com.shopping.util.Result;
import com.shopping.webdata.dto.JsoupDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 使用 Jsoup 获取网页数据
 * @author wangjian
 * @version 1.0
 * @see JsoupService
 * @since JDK1.8
 */
@FeignClient(value = "mall-webdata")
public interface JsoupService {

    @ApiOperation(value = "数据获取", tags = "数据获取")
    @GetMapping(value = "/jsoup/getInventoryWms")
    Result<JsoupDto> getInventoryWmsValue(@RequestParam(value = "url") String url);
}
