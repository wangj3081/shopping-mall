package com.shopping.inventory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.shopping.InventoryApplication;
import com.shopping.integral.service.IntegralService;
import com.shopping.inventory.dto.InventoryGoodsDto;
import com.shopping.inventory.entity.InventoryGoodsEntity;
import com.shopping.inventory.service.InventoryGoodsService;
import com.shopping.order.dto.OrderGoodsDto;
import com.shopping.util.Result;
import com.shopping.webdata.JsoupService;
import com.shopping.webdata.dto.JsoupDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wangjian
 * @version 1.0
 * @see InventoryTest
 * @since JDK1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InventoryApplication.class)
public class InventoryTest {

    @Resource
    private InventoryGoodsService inventoryGoodsService;
    @Resource
    private JsoupService jsoupService;
//    @Resource
    private IntegralService integralService;
    @Resource
    private DataSource dataSource;

    @Test
    public void testDataSource() {
        System.out.println("--------------------");
        try {
            System.err.println(dataSource.getConnection().getCatalog());
            System.err.println("--------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insertTest() {
        String text = "[{\"amount\":160,\"changeNum\":2,\"goodsName\":\"雅诗兰黛净莹柔肤 洁面 乳30ml*3\",\"sku\":\"4455584\",\"storeName\":\"自营\",\"storeNo\":\"101\"},{\"amount\":110,\"changeNum\":2,\"goodsName\":\"JMsolution 莹润蜂胶洗面奶150ml\",\"sku\":\"4910610\",\"storeName\":\"非自营\",\"storeNo\":\"301\"}]";
//        String text = "[{\"amount\":160,\"changeNum\":2,\"goodsName\":\"雅诗兰黛净莹柔肤 洁面 乳30ml*3\",\"sku\":\"4455584\",\"storeName\":\"自营\",\"storeNo\":\"101\"}]";
        JSONArray objects = JSONArray.parseArray(text);
        List<OrderGoodsDto> orderList = objects.toJavaList(OrderGoodsDto.class);
        inventoryGoodsService.updateAvailableNumListByStorageNo(orderList);
    }


    @Test
    public void queryPageList() {
        LambdaQueryChainWrapper<InventoryGoodsEntity> wrapper = inventoryGoodsService.lambdaQuery();
        List<InventoryGoodsEntity> list = wrapper.eq(InventoryGoodsEntity::getSku, "4455584").list();
        System.err.println(JSONObject.toJSONString(list));
    }


    @Test
    public void insertGoodsList() {
        String url = "http://search.jumei.com/?filter=0-11-1&search=%E6%B4%81%E9%9D%A2&from=search_toplist_%E6%B4%81%E9%9D%A2_word_pos_2&cat=";
        Result<JsoupDto> result = jsoupService.getInventoryWmsValue(url);
        JsoupDto data = result.getData();
        List<InventoryGoodsDto> inventoryList = data.getInventoryGoodsDtoList();
        List<InventoryGoodsEntity> targetList = new ArrayList<>();
        for (InventoryGoodsDto entity : inventoryList) {
            InventoryGoodsEntity target = new InventoryGoodsEntity();
            BeanUtils.copyProperties(entity, target);
            targetList.add(target);
        }
//        Integer resultVal = inventoryGoodsService.insertInventoryGoodsList(targetList);
        boolean bFlag = inventoryGoodsService.saveOrUpdateBatch(targetList);
        System.err.println("***************************:" + bFlag);
//        System.err.println(JSONObject.toJSONString(inventoryList));
    }
}
