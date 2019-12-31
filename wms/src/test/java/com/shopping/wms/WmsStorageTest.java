package com.shopping.wms;

import com.shopping.WmsApplication;
import com.shopping.util.Result;
import com.shopping.webdata.JsoupService;
import com.shopping.webdata.dto.JsoupDto;
import com.shopping.wms.dto.WmsStorageDto;
import com.shopping.wms.entity.WmsStorageEntity;
import com.shopping.wms.service.WmsStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see WmsStorageTest
 * @since JDK1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WmsApplication.class)
public class WmsStorageTest {

    @Resource
    private WmsStorageService wmsStorageService;

    @Resource
    private JsoupService jsoupService;

    @Test
    public void insertWmsList() {
        String url = "http://search.jumei.com/?filter=0-11-1&search=%E6%B4%81%E9%9D%A2&from=search_toplist_%E6%B4%81%E9%9D%A2_word_pos_2&cat=";
        Result<JsoupDto> jsoupResult = jsoupService.getInventoryWmsValue(url);
        JsoupDto data = jsoupResult.getData();
        List<WmsStorageDto> wmsStorageDtoList = data.getWmsStorageDtoList();
        List<WmsStorageEntity> insertList = new ArrayList<>();
        for (WmsStorageDto entity : wmsStorageDtoList) {
            WmsStorageEntity storageEntity = new WmsStorageEntity();
            BeanUtils.copyProperties(entity, storageEntity);
            insertList.add(storageEntity);
        }
        Integer result = wmsStorageService.insertWmsStorageList(insertList);
        System.err.println("************" + result);
    }
}
