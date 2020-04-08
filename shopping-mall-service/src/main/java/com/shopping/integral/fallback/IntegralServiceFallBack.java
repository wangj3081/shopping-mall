package com.shopping.integral.fallback;

import com.shopping.integral.dto.IntegralDto;
import com.shopping.integral.service.IntegralService;
import com.shopping.util.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName IntegralServiceFallBack
 * @Description 熔断方法
 * @Author wangjian
 * @Date 2020/4/8 5:11 下午
 * @Version 1.0
 **/
@Component
public class IntegralServiceFallBack implements IntegralService {
    @Override
    public List<IntegralDto> queryListByStorageNo(String storageNo) {
        return null;
    }

    @Override
    public String echo(String message) {
        return null;
    }
}
