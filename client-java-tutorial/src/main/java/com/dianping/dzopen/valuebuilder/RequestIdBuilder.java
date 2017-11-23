package com.dianping.dzopen.valuebuilder;

import org.apache.commons.lang3.RandomStringUtils;


public class RequestIdBuilder implements ParamValueBuilder<String> {

    @Override
    public String build() {
        return RandomStringUtils.randomAlphabetic(16);
    }
}
