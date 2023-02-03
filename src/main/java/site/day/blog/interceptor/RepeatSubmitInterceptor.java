package site.day.blog.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import site.day.blog.annotation.RepeatSubmit;
import site.day.blog.constant.RedisConst;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.filter.wrapper.BodyReaderRequestWrapper;
import site.day.blog.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Description 重复提交检测
 * @ClassName RepeatSubmitInterceptor
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Log4j2
public class RepeatSubmitInterceptor implements HandlerInterceptor {

    /**
     * @return boolean
     * @Description 拦截repeatSubmit注解
     * @Author 23DAY
     * @Date 2022/10/13 9:51
     * @Param [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object]
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
//                如果是重复提交则拦截
                if (this.isRepeatSubmit(request, annotation)) {
                    log.warn(method.getName() + " 重复提交");
                    WebUtil.render(response, JsonUtil.Object2String(ResponseAPI.fail(StatusCodeEnum.API_REPEAT_SUBMIT)));
                    return false;
                }
            }
        }
        return true;
    }

    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @return boolean
     * @Description 验证是否重复提交由子类实现具体的防重复提交的规则
     * @Author 23DAY
     * @Date 2022/10/12 23:59
     * @Param [javax.servlet.http.HttpServletRequest, site.day.blog.annotation.RepeatSubmit]
     **/
    public Boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) throws IOException {

        // 获取本次请求参数并封装到map中
        String nowParams = "";
        if (request instanceof BodyReaderRequestWrapper) {
            BodyReaderRequestWrapper bodyReaderRequest = (BodyReaderRequestWrapper) request;
            nowParams = WebUtil.getRequestBody(bodyReaderRequest);
        }
        // body参数为空，获取Parameter的数据
        if (StringUtil.isEmpty(nowParams)) {
            nowParams = JsonUtil.Map2String(request.getParameterMap());
        }
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        // 请求地址（作为存放cache的key值）
        String url = request.getRequestURI();

        // 唯一值（没有消息头则使用请求地址）
        String submitKey = String.valueOf(request.getSession().getId());

        // 唯一标识（指定key + url + 消息头）
        String cacheRepeatKey = RedisConst.REPEAT_SUBMIT_KEY + url + submitKey;

        // 获取redis缓存的数据 并且判断是否为重复提交
        Object obj = redisUtil.get(cacheRepeatKey);
        if (obj != null) {
            Map<String, Object> sessionMap = (Map<String, Object>) obj;
            if (sessionMap.containsKey(url)) {
                Map<String, Object> preDataMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap, annotation.interval())) {
                    return true;
                }
            }
        }
        // 将新的请求缓存
        Map<String, Object> cacheMap = new HashMap<String, Object>();
        cacheMap.put(url, nowDataMap);
        redisUtil.set(cacheRepeatKey, cacheMap, annotation.interval(), TimeUnit.MILLISECONDS);
        return false;
    }

    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap, int interval) {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        return (time1 - time2) < interval;
    }

}
