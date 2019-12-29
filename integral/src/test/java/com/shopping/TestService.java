package com.shopping;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shopping.integral.entity.IntegralRecordEntity;
import com.shopping.integral.service.IntegralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

    @Resource
    private IntegralService integralService;

    @Test
    public void testUpdate() {
        IntegralRecordEntity entity = new IntegralRecordEntity();
        LambdaUpdateWrapper<IntegralRecordEntity> updateWrapper = new LambdaUpdateWrapper(entity);
        updateWrapper.eq(IntegralRecordEntity::getId, "2222");
        updateWrapper.set(IntegralRecordEntity::getStorageNo, "8888");
        boolean update = integralService.update(updateWrapper);
        System.err.println("*******************" + update);
    }
}
