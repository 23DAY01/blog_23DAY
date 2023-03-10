package site.day.blog.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @Description
 * @ClassName StringUtil
 * @Author 23DAY
 * @Date 2022/10/12 22:45
 * @Version 1.0
 */
public class StringUtil {

    /**
     * @return java.lang.Boolean
     * @Description 判断字符串是否为空或者未被定义
     * @Author 23DAY
     * @Date 2022/10/12 22:46
     * @Param [java.lang.String]
     **/
    public static Boolean isNullOrUndefined(String str) {
        return StrUtil.isNullOrUndefined(str);
    }

    /**
     * @Description
     * @Author 23DAY
     * @Date 2023/1/26 0:00
     * @Param [java.lang.String]
     * @Return java.lang.Boolean
     **/

    public static Boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }


    /**
     * @return java.lang.Boolean
     * @Description 判断是否为空
     * @Author 23DAY
     * @Date 2022/10/12 23:05
     * @Param [java.lang.String]
     **/
    public static Boolean isEmpty(String str) {
        return StrUtil.isEmpty(str);
    }

    /**
     * @Description 字符串为空
     * @Author 23DAY
     * @Date 2023/2/2 19:04
     * @Param [java.lang.String]
     * @Return java.lang.Boolean
     **/
    public static Boolean isBlank(String str) {
        return StrUtil.isBlank(str);
    }

    /**
     * @Description 字符串不为空
     * @Author 23DAY
     * @Date 2023/2/2 19:04
     * @Param [java.lang.String]
     * @Return java.lang.Boolean
     **/
    public static Boolean isNotBlank(String str) {
        return StrUtil.isNotBlank(str);
    }

    /**
     * @return java.lang.String
     * @Description 获取uuid
     * @Author 23DAY
     * @Date 2022/10/12 23:06
     * @Param []
     **/
    public static String uuid() {
        return StrUtil.uuid();
    }

    /**
     * @return java.lang.String
     * @Description 去除前缀
     * @Author 23DAY
     * @Date 2022/10/12 23:06
     * @Param []
     **/
    public static String removePrefix(String str, String prefix) {
        return StrUtil.removePrefix(str, prefix);
    }

    /**
     * @return java.lang.Boolean
     * @Description 是否以某个字符串开始
     * @Author 23DAY
     * @Date 2022/10/13 10:49
     * @Param [java.lang.String, java.lang.String]
     **/
    public static Boolean startsWithIgnoreCase(String contentType, String applicationJsonValue) {
        return StrUtil.startWithIgnoreCase(contentType, applicationJsonValue);
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * @return java.lang.String
     * @Description 截取某个字符串之前的字符
     * @Author 23DAY
     * @Date 2022/10/14 17:11
     * @Param [java.lang.String, java.lang.String]
     **/
    public static String substringBeforeLast(String str, String separator) {
        return StrUtil.subBefore(str, separator, true);
    }

    /**
     * @return java.lang.String
     * @Description 截取某个字符串之前的字符
     * @Author 23DAY
     * @Date 2022/10/14 17:11
     * @Param [java.lang.String, java.lang.String]
     **/
    public static String substringBefore(String str, String separator) {
        return StrUtil.subBefore(str, separator, false);
    }

    /**
     * @Description 获取括号内的内容
     * @Author 23DAY
     * @Date 2023/2/2 15:35
     * @Param [java.lang.String]
     * @Return java.lang.String
     **/
    public static String getBracketsContent(String str) {
        return str.substring(str.indexOf("(") + 1, str.indexOf(")"));
    }


}
