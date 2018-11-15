package com.lenovo.ecr.hybirdcloud.uds.common.util;

import java.util.TimeZone;

public class DateUtil {
    private DateUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static long getZero()
    {
        long current = System.currentTimeMillis();
        long zero = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
        return zero;
    }
}
