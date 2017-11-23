package com.dianping.dzopen.apis;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商户API单元测试
 */
public class ShopAPITest {
    private static Logger log = LoggerFactory.getLogger(ShopAPITest.class);

    @Test
    public void queryCitiesTest() throws Exception {
        String response = new ShopAPI().queryCities();
        log.debug(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void queryPoiDetailTest() throws Exception {
        String response = new ShopAPI().queryPoiDetail();
        log.debug(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void queryRegionsTest() throws Exception {
        String response = new ShopAPI().queryRegions();
        log.debug(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void queryCategoriesTest() throws Exception {
        String response = new ShopAPI().queryCategories();
        log.debug(response);
        Assert.assertNotNull(response);
    }
}
