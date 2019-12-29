package com.shopping.inventory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.inventory.entity.InventoryGoodsEntity;
import com.shopping.order.dto.OrderGoodsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存商品 DAO
 * @author wangjian
 * @version 1.0
 * @see InventoryGoodsDao
 * @since JDK1.8
 */
@Mapper
public interface InventoryGoodsDao extends BaseMapper<InventoryGoodsEntity> {

    /**
     * 根据店铺编码、批量更新库存商品集合
     * @param goodsEntityList
     */
    void updateStorageNumListByStorageNo(@Param(value = "list") List<OrderGoodsDto> goodsEntityList);

    /**
     * 根据店铺编码、SKU 更新库存商品集合
     * @param goodsEntityList
     */
    void updateAvailableNumListByStorageNo(@Param(value = "list") List<OrderGoodsDto> goodsEntityList);

}
