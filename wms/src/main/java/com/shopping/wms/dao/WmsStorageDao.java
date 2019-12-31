package com.shopping.wms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.inventory.dto.InventoryGoodsDto;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.wms.entity.WmsStorageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * wms 仓储服务 DAO
 * @author wangjian
 * @version 1.0
 * @see WmsStorageDao
 * @since JDK1.8
 */
@Mapper
public interface WmsStorageDao extends BaseMapper<WmsStorageEntity> {

    /**
     * 订单支付成功后扣减仓储库存，招揽发货
     * @param list
     * @return
     */
    Integer wmsStorageUpdateListByStoreNo(@Param(value = "list") List<OrderGoodsDto> list);

    /**
     * 订单支付成功后根据店铺编码、SKU扣减库存，招揽发货
     * @param goodsDto
     * @return
     */
    Integer wmsStorageUpdateByStoreNo(OrderGoodsDto goodsDto);
}
