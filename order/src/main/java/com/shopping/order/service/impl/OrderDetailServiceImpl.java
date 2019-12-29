
package com.shopping.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.order.dao.OrderDetailDao;
import com.shopping.order.entity.OrderDetailEntity;
import com.shopping.order.service.OrderDetailService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see OrderDetailServiceImpl
 * @since JDK1.8
 */
@Slf4j
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetailEntity>
        implements OrderDetailService {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer insertOrderDetailList(List<OrderDetailEntity> detailList) {
        log.info("============orderDetail==============");
        log.info("【XID】:{}", RootContext.getXID());
        if (!CollectionUtils.isEmpty(detailList)){
            return this.saveBatch(detailList) ? 1 : 0;
        }

        return 0;
    }
}
