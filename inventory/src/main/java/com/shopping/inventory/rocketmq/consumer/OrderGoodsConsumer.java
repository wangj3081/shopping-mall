package com.shopping.inventory.rocketmq.consumer;

import com.alibaba.fastjson.JSONArray;
import com.shopping.inventory.rocketmq.topic.RocketMQTopic;
import com.shopping.inventory.service.InventoryGoodsService;
import com.shopping.order.dto.OrderGoodsDto;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderGoodsConsumer
 * @since JDK1.8
 */
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}", topic = RocketMQTopic.ORDER_GOODS_TOPIC, consumerGroup = "${rocketmq.consumer.group}")
@Service
public class OrderGoodsConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Resource
    private InventoryGoodsService inventoryGoodsService;

    /**
     * 目前暂时不知道消费者在实现 RocketMQPushConsumerLifecycleListener 为什么还一定要实现一个 RocketMQListener 接口重写这个方法
     * 虽然在消费 broker 数据的时候不会运行它，但是必须要这么做，不然会抛出 rocketmq 的异常
     * @param value
     */
    @Override
    public void onMessage(String value) {
        /*List<OrderGoodsDto> orderGoodsList = JSONArray.parseArray(value, OrderGoodsDto.class);
        // 扣减商品库存
        inventoryGoodsService.updateStorageNumListByStorageNo(orderGoodsList);
        */
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @SneakyThrows
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExts, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : messageExts) {
                    String value = new String(messageExt.getBody());
                    List<OrderGoodsDto> orderGoodsList = JSONArray.parseArray(value, OrderGoodsDto.class);
                    try {
                        // 扣减商品库存
                        inventoryGoodsService.updateStorageNumListByStorageNo(orderGoodsList);
                    } catch (Exception e) {
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                    System.err.println(value);
                }
                // 提交之后才会更新 offset(消费消息的偏移量)，如果消费处理失败，也可以重复消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
