package com.shopping.rocketmq.topic;

/**
 * Topic 定义
 * @author wangjian
 * @version 1.0
 * @see RocketMQTopic
 * @since JDK1.8
 */
public interface RocketMQTopic {
    /**
     * 订单商品队列
     */
    String ORDER_GOODS_TOPIC = "order_goods_topic";
}
