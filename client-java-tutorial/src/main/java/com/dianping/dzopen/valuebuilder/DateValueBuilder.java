package com.dianping.dzopen.valuebuilder;

import com.dianping.dzopen.constants.AppConstants;
import com.dianping.dzopen.utils.DateUtil;

import java.util.Date;

public class DateValueBuilder implements ParamValueBuilder<String> {

    @Override
    public String build() {
        return DateUtil.formatDate(new Date(System.currentTimeMillis()), AppConstants.DATE_PATTERN);
    }
}
