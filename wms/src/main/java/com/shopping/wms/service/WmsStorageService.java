package com.shopping.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.protobuf.ServiceException;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.wms.entity.WmsStorageEntity;

import java.util.List;

/**
 * 仓储服务服务方法
 * @author wangjian
 * @version 1.0
 * @see WmsStorageFeignService
 * @since JDK1.8
 */
public interface WmsStorageService extends IService<WmsStorageEntity> {

    /**
     * 批量插入集合数据
     * @param listVal
     * @return
     */
    Integer insertWmsStorageList(List<WmsStorageEntity> listVal);

    /**
     * 批量更新仓储数据信息
     * @param list
     */
    Integer updateWmsStorageList(List<OrderGoodsDto> list) throws ServiceException;
}
