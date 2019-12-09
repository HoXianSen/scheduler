package com.hxs.scheduler.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatHelper {
    public static String yMd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String yMdHms(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String now_yMdHms() {
        return yMdHms(new Date());
    }
}
