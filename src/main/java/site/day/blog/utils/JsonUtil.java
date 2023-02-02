package site.day.blog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.Map;

/**
 * @Description json处理工具 jackson版本
 * @ClassName JsonUtil
 * @Author 23DAY
 * @Date 2022/9/15 11:09
 * @Version 1.0
 */
public class JsonUtil {

    private final static ObjectMapper objectMapper=new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * @Description string -> json
     * @Author 23DAY
     * @Date 2022/9/15 11:30
     * @Param [java.lang.String]
     * @return java.lang.String
     **/
    public static String String2Json(String s) throws JsonProcessingException {
        return objectMapper.writeValueAsString(s);
    }

    /**
     * @Description class -> json
     * @Author 23DAY
     * @Date 2022/9/15 11:40
     * @Param [java.lang.Object]
     * @return java.lang.String
     **/
    public static String Object2String(Object o) throws JsonProcessingException {
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(o);
    }

    /**
     * @Description json -> class
     * @Author 23DAY
     * @Date 2022/9/15 11:40
     * @Param [java.lang.String, java.lang.Class<?>]
     * @return java.lang.Object
     **/
    public static Object String2Object(String s,Class<?> c) throws JsonProcessingException {
        return objectMapper.readValue(s,c);
    }

    /**
     * @Description Objects -> String
     * @Author 23DAY
     * @Date 2022/9/15 11:53
     * @Param [java.util.List<java.lang.Object>]
     * @return java.lang.String
     **/
    public static String Objects2String(List<Object> list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }


    /**
     * @Description Map -> String
     * @Author 23DAY
     * @Date 2022/9/18 10:40
     * @Param [java.util.Map<?,?>]
     * @return java.lang.String
     **/
    public static String Map2String(Map<?,?> map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }






}
