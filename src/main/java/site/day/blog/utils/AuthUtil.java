package site.day.blog.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import site.day.blog.pojo.dto.UserDetail;

/**
 * @Description
 * @ClassName AuthUtil
 * @Author 23DAY
 * @Date 2022/9/17 10:02
 * @Version 1.0
 */
public class AuthUtil {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetail getLoginUser() {
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
