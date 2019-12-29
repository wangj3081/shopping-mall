package com.shopping.integral.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.integral.entity.IntegralRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 *  积分服务 Dao
 * @author wangjian
 * @version 1.0
 * @see IntegralRecordDao
 * @since JDK1.8
 */
@Mapper
public interface IntegralRecordDao extends BaseMapper<IntegralRecordEntity> {
}
