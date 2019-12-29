package com.shopping.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.inventory.entity.InventoryGoodsEntity;
import com.shopping.order.dto.OrderGoodsDto;

import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see InventoryGoodsService
 * @since JDK1.8
 */
public interface InventoryGoodsService extends IService<InventoryGoodsEntity> {

    /**
     * 批量插入商品库存
     * @param goodsList
     * @return
     */
    Integer insertInventoryGoodsList(List<InventoryGoodsEntity> goodsList);

    /**
     * 批量更新库存存量数据
     * @param goodsList
     */
    void updateStorageNumListByStorageNo(List<OrderGoodsDto> goodsList);

    /**
     * 批量更新库存可用数据
     * @param goodsEntityList
     */
    void updateAvailableNumListByStorageNo(List<OrderGoodsDto> goodsEntityList);
}
