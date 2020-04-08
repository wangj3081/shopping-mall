package com.shopping.order.controller;


import com.google.protobuf.ServiceException;
import com.shopping.integral.dto.IntegralDto;
import com.shopping.integral.service.IntegralService;
import com.shopping.order.dto.OrderPayDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.service.OrderMainService;
import com.shopping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public Result<String> createOrder(@RequestBody OrderReq orderReq) throws ServiceException {
        String orderNo = orderMainService.createOrder(orderReq);
        return new Result<String>().success(orderNo);
    }

    @ApiOperation(value = "获取订单编码", httpMethod = "POST")
    @PostMapping(value = "getOrderNo")
    public Result<String> getOrderNo() {
        try {
//            log.info("【进入获取订单方法】");
//            throw new RuntimeException("异常了");
            String orderNo = orderMainService.getOrderNo();
            return new Result<String>().success(orderNo);
        } catch (Exception e) {
            log.error("【获取订单编码异常】:「{}」", e);
            return new Result<String>().error("-1", "获取订单异常");
        }
    }


    @ApiOperation(value = "订单支付,支付成功返回支付编码", httpMethod = "POST")
    @PostMapping(value = "orderPay")
    public Result<String> orderPay(@RequestBody OrderPayDto orderPayDto) throws ServiceException {
        // 获取支付编码
        String payNo = orderMainService.getPayNo();
        orderMainService.orderPay(orderPayDto.getOrderNo(), payNo);
        return new Result<String>().success("SUCCESS");
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
