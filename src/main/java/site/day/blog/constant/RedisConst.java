package site.day.blog.constant;

/**
 * @Description Redis 常量
 * @ClassName RedisPrefixConst
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class RedisConst {

    //============================prefix===============================

    /**
     * 验证码
     */
    public static final String USER_CODE_KEY = "code:";

    /**
     * 用户登录
     */
    public static final String USER_LOGIN = "login:";

    /**
     * 重复提交
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 接口访问频繁
     */
    public static final String API_ACCESS_RESTRICTION = "api_access_restriction:";

    /**
     * 用户是否点赞
     */
    public static final String ARTICLE_USER_LIKE = "article_user_like:";

    /**
     * 文章点赞量
     */
    public static final String ARTICLE_LIKE_COUNT = "article_like_count:";


    //==============================key================================


    /**
     * 用户地区
     */
    public static final String USER_AREA = "user_area";

    /**
     * 访客地区
     */
    public static final String VISITOR_AREA = "visitor_area";

    /**
     * 访客
     */
    public static final String BLOG_VISITOR = "blog_visitor";

    /**
     * 文章浏览量
     */
    public static final String ARTICLE_VIEW_COUNT = "article_view_count";

    /**
     * 博客浏览量
     */
    public static final String BLOG_VIEW_COUNT = "blog_view_count";


}
