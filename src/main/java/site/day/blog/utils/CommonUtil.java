package site.day.blog.utils;

import java.util.*;

/**
 * @Description
 * @ClassName CommonUtil
 * @Author 23DAY
 * @Date 2023/1/27 22:16
 * @Version 1.0
 */
public class CommonUtil {

    public static String getRandomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }


}
