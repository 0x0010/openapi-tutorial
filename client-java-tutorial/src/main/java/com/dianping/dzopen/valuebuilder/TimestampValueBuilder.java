package com.dianping.dzopen.valuebuilder;

import com.dianping.dzopen.constants.AppConstants;
import com.dianping.dzopen.utils.DateUtil;

import java.util.Date;

public class TimestampValueBuilder implements ParamValueBuilder<String> {

    @Override
    public String build() {
        return DateUtil.formatDate(new Date(System.currentTimeMillis()), AppConstants.TIMESTAMP_PATTERN);
    }
}
