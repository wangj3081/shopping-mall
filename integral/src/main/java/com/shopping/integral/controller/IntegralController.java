package com.shopping.integral.controller;

import com.shopping.integral.dto.IntegralDto;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.entity.ShardingIntegralRecordEntity;
import com.shopping.integral.service.IntegralService;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 积分服务控制器
 * @author wangjian
 * @version 1.0
 * @see IntegralController
 * @since JDK1.8
 */
@Api(tags = "积分服务接口")
@RestController
@RequestMapping(value = "/integral")
public class IntegralController {

    @Resource(name = "integralServiceImpl")
    private IntegralService integralService;


    @GetMapping(value = "/queryListByStorageNo")
    public List<IntegralDto> queryListByStorageNo(@RequestParam(name = "storageNo") String storageNo) {
        List<ShardingIntegralRecordEntity> listValue = integralService.queryListByStorageNo(storageNo);
        List<IntegralDto> resultList = convertList2List(listValue, IntegralDto.class);
        return resultList;
    }

    @GetMapping(value = "/echo")
    public String echo(@RequestParam(value = "message") String echo) {
        return "Echo:" + echo;
    }

    private  <E, T> List<T> convertList2List(List<E> input, Class<T> clzz) {
        List<T> output = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(input)) {
            for (E source : input) {
                T target = BeanUtils.instantiate(clzz);
                BeanUtils.copyProperties(source, target);
                output.add(target);
            }
        }
        return output;
    }
}
