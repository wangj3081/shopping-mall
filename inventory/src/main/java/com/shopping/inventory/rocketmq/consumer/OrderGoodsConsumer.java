package com.shopping.inventory.rocketmq.consumer;

import com.alibaba.fastjson.JSONArray;
import com.shopping.inventory.rocketmq.topic.RocketMQTopic;
import com.shopping.inventory.service.InventoryGoodsService;
import com.shopping.order.dto.OrderGoodsDto;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
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
public class OrderGoodsConsumer implements RocketMQListener<String> {

    @Resource
    private InventoryGoodsService inventoryGoodsService;

    @Override
    public void onMessage(String value) {
        List<OrderGoodsDto> orderGoodsList = JSONArray.parseArray(value, OrderGoodsDto.class);
        // 扣减商品库存
        inventoryGoodsService.updateStorageNumListByStorageNo(orderGoodsList);
    }
}
