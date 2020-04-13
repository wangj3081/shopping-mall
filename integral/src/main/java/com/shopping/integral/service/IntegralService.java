package com.shopping.integral.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.entity.ShardingIntegralRecordEntity;

import java.util.List;

/**
 * 积分服务接口
 * @author wangjian
 * @version 1.0
 * @see IntegralService
 * @since JDK1.8
 */
public interface IntegralService extends IService<ShardingIntegralRecordEntity> {

    /**
     * 根据店铺编码获取店铺积分列表
     * @param storageNo
     * @return
     */
    List<ShardingIntegralRecordEntity> queryListByStorageNo(String storageNo);
}
