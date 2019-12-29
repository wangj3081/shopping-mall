package com.shopping.order.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.shopping.integral.dto.IntegralDto;
import com.shopping.integral.service.IntegralService;
import com.shopping.order.dto.OrderPayDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.entity.OrderMainEntity;
import com.shopping.order.service.OrderMainService;
import com.shopping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 订单服务
 * @author wangjian
 * @version 1.0
 * @see OrderController
 * @since JDK1.8
 */
@Slf4j
@Api(value = "订单rest服务")
@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Resource
    private IntegralService integralService;
    @Resource
    private OrderMainService orderMainService;

    @ApiOperation(value = "创建订单", httpMethod = "POST")
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    public Result<String> createOrder(@RequestBody OrderReq orderReq) {
        String orderNo = orderMainService.createOrder(orderReq);
        return new Result<String>().success(orderNo);
    }

    @ApiOperation(value = "获取订单编码", httpMethod = "POST")
    @PostMapping(value = "getOrderNo")
    public Result<String> getOrderNo() {
        try {
            String orderNo = orderMainService.getOrderNo();
            return new Result<String>().success(orderNo);
        } catch (Exception e) {
            log.error("【获取订单编码异常】:「{}」", e);
            return new Result<String>().error("-1", "获取订单异常");
        }
    }

    @ApiOperation(value = "订单支付,支付成功返回支付编码", httpMethod = "POST")
    @PostMapping(value = "orderPay")
    public Result<String> orderPay(@RequestBody OrderPayDto orderPayDto) {
        // 获取支付编码
        try {
            String payNo = orderMainService.getPayNo();
            orderMainService.orderPay(orderPayDto.getOrderNo(), payNo);
            return new Result<String>().success("SUCCESS");
        } catch (Exception e) {
            return new Result<String>().error("-1", "订单支付处理失败");
        }
    }


    @RequestMapping(value = "queryIntegralList", method = RequestMethod.GET)
    public List<IntegralDto> queryIntegralList(@RequestParam(value = "storageNo") String storageNo) {
        List<IntegralDto> result = integralService.queryListByStorageNo(storageNo);
        return result;
    }




    /*
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancerClient balancerClient;
    @Resource
    private RestTemplate restTemplate;*/
    @GetMapping(value = "echo")
    public String echo(@RequestParam(value = "message") String message) {
       /* String serviceName = "mall-gateway";
        ServiceInstance choose = balancerClient.choose(serviceName);
        URI uri = choose.getUri();
        System.out.println("" + choose.getHost() + " : " + choose.getPort() + " : " + uri + " : " + choose.getServiceId());
        String result = "";
        if (uri != null) {
            String url = uri + "/integral/echo?message=" + message;
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
            result = forEntity.getBody();
        }*/
        String result = integralService.echo(message);
        return result;
    }

}
