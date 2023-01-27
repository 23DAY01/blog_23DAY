package site.day.blog.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import site.day.blog.annotation.AccessLimit;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.utils.JsonUtil;
import site.day.blog.utils.RedisUtil;
import site.day.blog.utils.ResponseAPI;
import site.day.blog.utils.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static site.day.blog.constant.RedisConst.API_ACCESS_RESTRICTION;

/**
 * @Description API限流
 * @ClassName ApiAccessRestrictionInterceptor
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Log4j2
public class ApiAccessRestrictionInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 如果请求输入方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit != null) {
                long seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                // 关于key的生成规则可以自己定义 本项目需求是对每个方法都加上限流功能，如果你只是针对ip地址限流，那么key只需要只用ip就好
                String key = API_ACCESS_RESTRICTION + WebUtil.getIpAddress(httpServletRequest) + hm.getMethod().getName();
                // 从redis中获取用户访问的次数
                // 此操作代表获取该key对应的值自增1后的结果
                long q = redisUtil.incrExpire(key, seconds);
                if (q > maxCount) {
                    WebUtil.render(httpServletResponse, JsonUtil.Object2String(ResponseAPI.fail(StatusCodeEnum.API_ACCESS_FREQUENT)));
                    log.warn(key + "请求次数超过每" + seconds + "秒" + maxCount + "次");
                    return false;
                }
                return true;
            }
        }
        return true;
    }
}
