
package com.shopping.integral.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.integral.dao.IntegralRecordDao;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.entity.ShardingIntegralRecordEntity;
import com.shopping.integral.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see IntegralServiceImpl
 * @since JDK1.8
 */
@Service(value = "integralServiceImpl")
public  class IntegralServiceImpl extends ServiceImpl<IntegralRecordDao, ShardingIntegralRecordEntity>
        implements IntegralService{

    @Autowired
    private IntegralRecordDao recordDao;


    @Override
    public List<ShardingIntegralRecordEntity> queryListByStorageNo(String storageNo) {
        LambdaQueryWrapper<ShardingIntegralRecordEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShardingIntegralRecordEntity::getStorageNo, storageNo);
        List<ShardingIntegralRecordEntity> resultList = this.recordDao.selectList(wrapper);
        return resultList;
    }
}
