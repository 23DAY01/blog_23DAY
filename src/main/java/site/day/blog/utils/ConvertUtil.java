package site.day.blog.utils;

import cn.hutool.core.convert.Convert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static <T> List<T> objToList(Object obj, Class<T> cla) {
        List<T> list = new ArrayList<T>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(cla.cast(o));
            }
            return list;
        }
        return null;
    }

    public static <T> Set<T> objToSet(Object obj, Class<T> clazz) {
        Set<T> result = new HashSet<>();
        if (obj instanceof Set<?>) {
            for (Object o : (Set<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return result;
    }
}
