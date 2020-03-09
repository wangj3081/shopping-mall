package com.shopping.order.rocketmq.transaction;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.protobuf.ServiceException;
import com.shopping.order.entity.OrderDetailEntity;
import com.shopping.order.entity.OrderMainEntity;
import com.shopping.order.service.OrderDetailService;
import com.shopping.order.service.OrderMainService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 事务消息监听
 * @author wangjian
 * @version 1.0
 * @see RocketMQLocalTransactionState
 * @since JDK1.8
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "tx_order_group")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Resource
    private OrderDetailService orderDetailService;
    @Resource
    private OrderMainService orderMainService;

    /**
     * 处理本地事务,本地事务处理完成后提交消息
     * @param message
     * @param o
     * @return
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            OrderMainEntity orderMainEntity = new OrderMainEntity();
            MessageHeaders headers = message.getHeaders();
            String orderNo = headers.get("orderNo", String.class);
            String orderPayNo = headers.get("orderPayNo", String.class);
            LambdaUpdateWrapper<OrderMainEntity> wrapper = new LambdaUpdateWrapper(orderMainEntity);
            // 处理订单信息
            wrapper.eq(OrderMainEntity::getOrderNo, orderNo);
            wrapper.set(OrderMainEntity::getPayNo, orderPayNo);
            wrapper.set(OrderMainEntity::getOrderStatus, 1);
            orderMainService.update(wrapper);
            OrderDetailEntity detailEntity = new OrderDetailEntity();
            LambdaUpdateWrapper<OrderDetailEntity> detailWrapper = new LambdaUpdateWrapper(detailEntity);
            detailWrapper.eq(OrderDetailEntity::getOrderNo, orderNo);
            detailWrapper.set(OrderDetailEntity::getOrderStatus, 1);
            // 更新详情单号状态, 订单业务处理完成后发送队列消息到库存系统中
            orderDetailService.update(detailWrapper);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("【订单支付处理订单信息业务异常】： {}", e);
//            throw new ServiceException("订单支付处理订单信息业务异常");
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    /**
     * 检查本地事务状态
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String orderNo = (String) message.getHeaders().get("orderNo");
        // 处理订单信息
        OrderMainEntity entity = orderMainService.lambdaQuery().eq(OrderMainEntity::getOrderNo, orderNo).getEntity();
        if (entity.getOrderStatus() != 1) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
