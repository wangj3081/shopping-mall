package com.shopping.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.inventory.dao.InventoryGoodsDao;
import com.shopping.inventory.entity.InventoryGoodsEntity;
import com.shopping.inventory.service.InventoryGoodsService;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import com.shopping.wms.service.WmsStorageFeignService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 库存服务实现
 * @author wangjian
 * @version 1.0
 * @see InventoryGoodsServiceImpl
 * @since JDK1.8
 */
@Slf4j
@Service
public class InventoryGoodsServiceImpl extends ServiceImpl<InventoryGoodsDao, InventoryGoodsEntity>
        implements InventoryGoodsService {

    @Autowired
    private InventoryGoodsDao inventoryGoodsDao;

    @Resource
    private WmsStorageFeignService wmsStorageFeignService;

    @Override
    public Integer insertInventoryGoodsList(List<InventoryGoodsEntity> goodsList) {
        if (!CollectionUtils.isEmpty(goodsList)) {
            boolean b = this.saveBatch(goodsList);
            return b ? 1 : 0;
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStorageNumListByStorageNo(List<OrderGoodsDto> goodsList) {
        if (!CollectionUtils.isEmpty(goodsList)) {
            // seata 暂时不支持批量更新，只能使用循环单条数据更新
            // this.inventoryGoodsDao.updateStorageNumListByStorageNo(goodsList);
            for (OrderGoodsDto goodsDto : goodsList) {
                this.inventoryGoodsDao.updateStorageNumByStorageNo(goodsDto);
            }
            Result<String> result = wmsStorageFeignService.updateWmsStorageList(goodsList);
            if (!"0".equals(result.getCode())) {
                throw new RuntimeException("通知仓库发货异常: " + result.getMessage());
            }
        } else {
            throw new RuntimeException("更新的数据集合不能为空");
        }
    }

    /**
     * 开启一个新的事务
     * @param goodsEntityList
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateAvailableNumListByStorageNo(List<OrderGoodsDto> goodsEntityList) {
        log.info("============inventory==============");
        log.info("【XID】:{}", RootContext.getXID());
        if (!CollectionUtils.isEmpty(goodsEntityList)) {
            // seata 暂时不支持批量更新，只能使用循环单条数据更新
            // this.inventoryGoodsDao.updateAvailableNumListByStorageNo(goodsEntityList);
            for (OrderGoodsDto goodsDto : goodsEntityList) {
                this.inventoryGoodsDao.updateAvailableNumByStorageNo(goodsDto);
            }
        } else {
            throw new RuntimeException("更新的数据集合不能为空");
        }
    }


}
