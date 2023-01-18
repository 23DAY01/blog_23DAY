package site.day.blog.utils;

import cn.hutool.core.convert.Convert;

/**
 * @Description
 * @ClassName ConvertUtil
 * @Author 23DAY
 * @Date 2022/10/18 11:36
 * @Version 1.0
 */
public class ConvertUtil {
    public static String toStr(String parameter, String defaultValue) {
        return Convert.toStr(parameter,defaultValue);
    }

    public static Integer toInt(String parameter) {
        return Convert.toInt(parameter);
    }

    public static Integer toInt(String parameter, Integer defaultValue) {
        return Convert.toInt(parameter,defaultValue);
    }

    public static Boolean toBool(String parameter) {
        return Convert.toBool(parameter);
    }

    public static Boolean toBool(String parameter, Boolean defaultValue) {
        return Convert.toBool(parameter,defaultValue);
    }
}
