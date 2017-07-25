package com.kupa.hotel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mita on 2017/5/27.
 */

public class DateUtils {

    public static String formatYear(long date) {
        if (date > 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年", new Locale("zh", "CN"));
            return format.format(date);
        }
        return null;
    }

    /**
     * 获取当前周几
     *
     * @return 周几
     */
    public static String getWeek() {
        SimpleDateFormat format = new SimpleDateFormat("E", Locale.US);
        return format.format(new Date());
    }

    /**
     * 获取当前日期
     *
     * @return 日期
     */
    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd", new Locale("zh", "CN"));
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @return 时间
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm", new Locale("zh", "CN"));
        return sdf.format(new Date());
    }
}
