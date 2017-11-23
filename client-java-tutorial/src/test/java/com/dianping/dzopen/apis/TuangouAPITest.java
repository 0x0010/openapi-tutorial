package com.dianping.dzopen.apis;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TuangouAPITest {

    private static Logger log = LoggerFactory.getLogger(TuangouAPITest.class);

    @Test
    public void receiptPrepareTest() throws Exception {
        String returnContent = new TuangouAPI().receiptPrepare();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }

    @Test
    public void receiptConsumeTest() throws Exception {
        String returnContent = new TuangouAPI().receiptConsume();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }

    @Test
    public void receiptGetConsumedTest() throws Exception {
        String returnContent = new TuangouAPI().receiptGetConsumed();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }

    @Test
    public void receiptQueryListByDateTest() throws Exception {
        String returnContent = new TuangouAPI().receiptQueryListByDate();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }

    @Test
    public void receiptReverseConsumeTest() throws Exception {
        String returnContent = new TuangouAPI().receiptReverseConsume();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }

    @Test
    public void receiptScanPrepareTest() throws Exception {
        String returnContent = new TuangouAPI().receiptScanPrepare();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }

    @Test
    public void dealQueryShopDealTest() throws Exception {
        String returnContent = new TuangouAPI().dealQueryShopDeal();
        log.debug(returnContent);
        Assert.assertNotNull(returnContent);
    }
}
