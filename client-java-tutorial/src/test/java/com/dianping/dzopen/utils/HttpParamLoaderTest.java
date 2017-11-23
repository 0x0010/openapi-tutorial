package com.dianping.dzopen.utils;

import org.junit.Test;

public class HttpParamLoaderTest {

    @Test
    public void testParseXml() {
        HttpParamLoader.loadRequest("httpparams/tuangou/receipt-prepare.xml");

    }
}
