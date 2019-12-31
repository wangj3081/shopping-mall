package com.shopping.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.protobuf.ServiceException;
import com.shopping.inventory.service.InventoryService;
import com.shopping.order.dao.OrderMainDao;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.order.dto.OrderReq;
import com.shopping.order.entity.OrderDetailEntity;
import com.shopping.order.entity.OrderMainEntity;
import com.shopping.order.service.OrderDetailService;
import com.shopping.order.service.OrderMainService;
import com.shopping.util.CustomBeanAndSuperUtils;
import com.shopping.util.Result;
import com.shopping.wms.service.WmsStorageFeignService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.DefaultGlobalTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderMainServiceImpl
 * @since JDK1.8
 */
@Slf4j
@Service
public class OrderMainServiceImpl extends ServiceImpl<OrderMainDao, OrderMainEntity>
        implements OrderMainService {

    @Resource
    private OrderDetailService detailService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private WmsStorageFeignService wmsStorageService;
    /**
     * 用于创建订单做计数器处理，多台机器部署可替换为 redis
     */
    private AtomicLong atomicLong = new AtomicLong(0);
    /**
     * 订单编码前缀
     */
    private final String ORDER_PREFIX = "SPMDD";
    /**
     * 用于创建订单支付编码做计数器处理
     */
    private AtomicLong payNoLong = new AtomicLong(0);
    /**
     * 支付编码前缀
     */
    private final String PAY_NO_PREFIX = "PAY";

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer insertOrderMainList(List<OrderMainEntity> listValue) {
        log.info("============order==============");
        log.info("【XID】:{}", RootContext.getXID());
        if (!CollectionUtils.isEmpty(listValue)) {
            return this.saveBatch(listValue) ? 1 : 0;
        }
        return 0;
    }


//    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    @GlobalTransactional
    @Override
    public String createOrder(OrderReq orderReq) throws ServiceException {
        log.info("============order==============");
        log.info("【XID】:{}", RootContext.getXID());
        // 详情信息
        List<OrderGoodsDto> goodsDtoList = orderReq.getGoodsDtoList();
        List<OrderDetailEntity> detailList = CustomBeanAndSuperUtils.convertPojos(goodsDtoList, OrderDetailEntity.class);
        String orderNo = orderReq.getOrderNo();
        // 订单总金额
        BigDecimal orderAmount = new BigDecimal(0);
        for (OrderDetailEntity entity : detailList) {
            entity.setId(UUID.randomUUID().toString());
            entity.setOrderNo(orderNo);
            orderAmount = orderAmount.add(entity.getAmount());
        }
        OrderMainEntity orderMain = new OrderMainEntity();
        orderMain.setId(UUID.randomUUID().toString());
        // 订单编码
        orderMain.setOrderNo(orderNo);
        // 订单金额
        orderMain.setOrderAmount(orderAmount);
        // 订单创建时间
        orderMain.setCreateDate(orderReq.getCreateTime());
        // 收件地址
        orderMain.setReceiptAddress(orderReq.getReceiptAddress());
        // 收件人
        orderMain.setReceiptPeople(orderReq.getReceiptPeople());
        // 订单状态
        orderMain.setOrderStatus(0);
        // 写入订单表
        Integer result = insertOrderMainList(new ArrayList<OrderMainEntity>() {{
            add(orderMain);
        }});

        if (result > 0) {
            // 写入订单明细
            detailService.insertOrderDetailList(detailList);
            log.error("【店铺数据】:{}", JSONObject.toJSONString(goodsDtoList));
            // 扣减店铺可用
            Result<String> updateResult = inventoryService.updateAvailableNumGoodsList(goodsDtoList);
            if (!"0".equals(updateResult.getCode())){
                throw new ServiceException("更新库存错误:"+updateResult.getMessage());
            }
//            int index = 100/0;
        } else {
            return Result.ERROR_CODE;
        }
        return Result.SUCCESS_CODE;
    }

    /**
     * 获取订单编码
     * @return
     */
    @Override
    public String getOrderNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeStr = LocalDateTime.now().format(formatter);
        return ORDER_PREFIX + timeStr + atomicLong.incrementAndGet();
    }

    /**
     * 获取支付编码
     * @return
     */
    @Override
    public String getPayNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeStr = LocalDateTime.now().format(formatter);
        return PAY_NO_PREFIX + timeStr + payNoLong.incrementAndGet();
    }

    @GlobalTransactional
    @Override
    public String orderPay(String orderNo, String orderPayNo) throws ServiceException {
        OrderMainEntity orderMainEntity = new OrderMainEntity();
        LambdaUpdateWrapper<OrderMainEntity> wrapper = new LambdaUpdateWrapper(orderMainEntity);
        // 处理订单信息
        wrapper.eq(OrderMainEntity::getOrderNo, orderNo);
        wrapper.set(OrderMainEntity::getPayNo, orderPayNo);
        wrapper.set(OrderMainEntity::getOrderStatus, 1);
        boolean update = this.update(wrapper);
        OrderDetailEntity detailEntity = new OrderDetailEntity();
        LambdaUpdateWrapper<OrderDetailEntity> detailWrapper = new LambdaUpdateWrapper(detailEntity);
        detailWrapper.eq(OrderDetailEntity::getOrderNo, orderNo);
        detailWrapper.set(OrderDetailEntity::getOrderStatus, 1);
        // 更新详情单号状态
        detailService.update(detailWrapper);
        // 获取订单详情数据
        List<OrderDetailEntity> detailList = detailService.lambdaQuery().select().eq(OrderDetailEntity::getOrderNo, orderNo).list();
        List<OrderGoodsDto> orderGoodsDtos = CustomBeanAndSuperUtils.convertPojos(detailList, OrderGoodsDto.class);
        // 扣减库存信息
        Result<String> result = inventoryService.updateStorageNumGoodsList(orderGoodsDtos);
        if (!"0".equals(result.getCode())) {
            throw new ServiceException("扣减库存失败:" + result.getMessage());
        }
        // 通知仓库发货
        Result<String> wmsResult = wmsStorageService.updateWmsStorageList(orderGoodsDtos);
        if (!"0".equals(wmsResult.getCode())) {
            throw new ServiceException("扣减仓库库存失败:" + wmsResult.getMessage());
        }
        return "SUCCESS";
    }
}
