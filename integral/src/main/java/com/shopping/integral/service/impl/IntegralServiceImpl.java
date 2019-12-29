
package com.shopping.integral.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.integral.dao.IntegralRecordDao;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.service.IntegralService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see IntegralServiceImpl
 * @since JDK1.8
 */
@Service
public  class IntegralServiceImpl extends ServiceImpl<IntegralRecordDao, IntegralRecordEntity>
        implements IntegralService{


    @Override
    public List<IntegralRecordEntity> queryListByStorageNo(String storageNo) {
        LambdaQueryWrapper<IntegralRecordEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(IntegralRecordEntity::getStorageNo, storageNo);
        List<IntegralRecordEntity> resultList = this.baseMapper.selectList(wrapper);
        return resultList;
    }
}
