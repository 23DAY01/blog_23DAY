package site.day.blog.handler.securityHandler;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.utils.JsonUtil;
import site.day.blog.utils.ResponseAPI;
import site.day.blog.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description session非法策略
 * @ClassName InvalidSessionStrategyImpl
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class InvalidSessionStrategyImpl implements InvalidSessionStrategy {
 
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebUtil.render(response,JsonUtil.Object2String(ResponseAPI.fail(StatusCodeEnum.AUTH_SESSION_TIMEOUT)));
    }
 
}