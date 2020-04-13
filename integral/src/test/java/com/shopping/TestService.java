package com.shopping;

//import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.alibaba.fastjson.JSONObject;
import com.shopping.integral.dao.IntegralRecordDao;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.entity.ShardingIntegralRecordEntity;
import com.shopping.integral.service.IntegralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see TestService
 * @since JDK1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestService {

    @Resource(name = "integralServiceImpl")
    private IntegralService integralService;

    @Resource
    private IntegralRecordDao recordDao;


    @Test
    public void testQuery(){
        List<ShardingIntegralRecordEntity> result = integralService.queryListByStorageNo("2233344");
        System.err.println(JSONObject.toJSONString(result));
    }

    @Test
    public void insertOneTest() {
        ShardingIntegralRecordEntity entity = new ShardingIntegralRecordEntity();
        entity.setId(3L);
        entity.setStorageName("名字啊");
        entity.setIntegral(222L);
        entity.setOrderNo("123");
        entity.setPayNo("2233");
        entity.setStorageNo("2233344");
        entity.setAmount(new BigDecimal(2.00));
        Integer result = recordDao.insertIntegralRecord(entity);
        System.err.println(result);
    }


    @Test
    public void testUpdate() {
        IntegralRecordEntity entity = new IntegralRecordEntity();
//        LambdaUpdateWrapper<ShardingIntegralRecordEntity> updateWrapper = new LambdaUpdateWrapper(entity);
//        updateWrapper.eq(IntegralRecordEntity::getId, "2222");
//        updateWrapper.set(IntegralRecordEntity::getStorageNo, "8888");
//        boolean update = integralService.update(updateWrapper);
//        System.err.println("*******************" + update);
    }
}
