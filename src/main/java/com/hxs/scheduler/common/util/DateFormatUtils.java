package com.hxs.scheduler.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static String yMd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String yMdHms(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
