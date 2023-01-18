package site.day.blog.handler.securityHandler;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.utils.JsonUtil;
import site.day.blog.utils.ResponseAPI;
import site.day.blog.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description 认证失败处理器
 * @ClassName AuthenticationFailHandlerImpl
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
//        WebUtil.render(httpServletResponse, JsonUtil.Object2String(ResponseAPI.fail(StatusCode.CLIENT.AUTH_UorP_ERROR.getCode())));
        WebUtil.render(httpServletResponse, JsonUtil.Object2String(ResponseAPI.fail(StatusCodeEnum.getStatusCodeEnum(e.getMessage()))));
    }
}
