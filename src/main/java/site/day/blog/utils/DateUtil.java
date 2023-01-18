package site.day.blog.utils;

import cn.hutool.core.date.BetweenFormatter;

import java.util.Date;

/**
 * @Description
 * @ClassName DateUtil
 * @Author 23DAY
 * @Date 2022/10/19 15:38
 * @Version 1.0
 */
public class DateUtil {

    public static String parseDateToStr(String format, Date date) {
        return cn.hutool.core.date.DateUtil.format(date, format);
    }

    public static String getDateBetween(Date d1, Date d2) {
        return cn.hutool.core.date.DateUtil.formatBetween(d1,d2, BetweenFormatter.Level.MINUTE);
    }

    public static Date getNow(){
        return cn.hutool.core.date.DateUtil.date();
    }

}
