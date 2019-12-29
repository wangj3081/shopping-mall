package com.shopping;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.shopping.order.entity.OrderDetailEntity;
import com.shopping.order.service.OrderDetailService;
import com.shopping.util.Result;
import com.shopping.wms.service.WmsStorageFeignService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderTest
 * @since JDK1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderTest {
    @Resource
    private OrderDetailService detailService;

    @Resource
    private WmsStorageFeignService wmsStorageFeignService;

    @Test
    public void echoTest() {
        Result<String> result = wmsStorageFeignService.echo(new ArrayList<>());
        System.err.println(JSONObject.toJSONString(result));
        System.err.println("*******************************");
    }

    @Test
    public void queryDetailList() {
        String orderNo = "";
        LambdaQueryChainWrapper<OrderDetailEntity> orderDetailList = detailService.lambdaQuery().select().eq(OrderDetailEntity::getOrderNo, orderNo);
    }
}
