package site.day.blog.constant;


/**
 * @Description 公共 常量
 * @ClassName CommonConst
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class CommonConst {

    /**
     * 否
     */
    public static final Boolean FALSE = false;

    /**
     * 是
     */
    public static final Boolean TRUE = true;

    /**
     * 省
     */
    public static final String PROVINCE = "省";

    /**
     * 市
     */
    public static final String CITY = "市";

    /**
     * 未知的
     */
    public static final String UNKNOWN = "未知";


    /**
     * 分页常量
     */
    public static final String PAGE_CURRENT = "current";
    public static final String PAGE_SIZE = "size";
    public static final String PAGE_TOTAL = "total";
    public static final long PAGE_DEFAULT_CURRENT = 1L;
    public static final long PAGE_DEFAULT_SIZE = 10L;


    /**
     * 验证码过期时间
     */
    public static final long CODE_EXPIRE_TIME = 15 * 60;

    /**
     * 默认网站配置id
     */
    public static final Integer DEFAULT_WEBSITE_CONFIG_ID = 1;

    /**
     * 默认昵称
     */
    public static final String DEFAULT_NICKNAME = "游客";
    /**
     * 默认留言速度
     */
    public static final Integer MESSAGE_SPEED = 7;
}
