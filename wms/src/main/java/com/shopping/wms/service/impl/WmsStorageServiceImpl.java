package com.shopping.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.wms.dao.WmsStorageDao;
import com.shopping.wms.entity.WmsStorageEntity;
import com.shopping.wms.service.WmsStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see WmsStorageServiceImpl
 * @since JDK1.8
 */
@Service
public class WmsStorageServiceImpl extends ServiceImpl<WmsStorageDao, WmsStorageEntity> implements WmsStorageService {

    @Resource
    private WmsStorageDao wmsStorageDao;


    @Override
    public Integer insertWmsStorageList(List<WmsStorageEntity> listVal) {
        if (!CollectionUtils.isEmpty(listVal)) {
            boolean b = this.saveBatch(listVal);
            return  b ? 1 : 0;
        }
        return 0;
    }

    @Override
    public Integer updateWmsStorageList(List<OrderGoodsDto> list) {
        Integer result = wmsStorageDao.wmsStorageUpdateByStoreNo(list);
        return result;
    }
}
