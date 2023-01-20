package site.day.blog.constant;

/**
 * @Description 验证 常量
 * @ClassName AuthConst
 * @Author 23DAY
 * @Date 2022/9/19 19:19
 * @Version 1.0
 */
public class AuthConst {

    /**
     * 验证码
     */
    public static final String CAPTCHA = "kaptcha";

    /**
     * 记住我key
     */
    public static final String REMEMBER_ME_KEY = "remember_key";

    /**
     * token过期时间
     */
    public static final Integer TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 7;

}
