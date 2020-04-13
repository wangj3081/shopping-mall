package com.shopping.integral.dao;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.entity.ShardingIntegralRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *  积分服务 Dao
 * @author wangjian
 * @version 1.0
 * @see IntegralRecordDao
 * @since JDK1.8
 */
@Mapper
public interface IntegralRecordDao extends BaseMapper<ShardingIntegralRecordEntity> {

    int insertIntegralRecord(ShardingIntegralRecordEntity entity);

}
