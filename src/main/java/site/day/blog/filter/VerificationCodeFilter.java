package site.day.blog.filter;

/**
 * @Description
 * @ClassName VerificationCodeFilter
 * @Author 23DAY
 * @Date 2022/9/18 14:52
 * @Version 1.0
 */

import lombok.SneakyThrows;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import site.day.blog.constant.AuthConst;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.VerificationCodeException;
import site.day.blog.handler.securityHandler.AuthenticationFailHandlerImpl;
import site.day.blog.utils.StringUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description 基于过滤器实现图形验证码
 * @ClassName VerificationCodeFilter
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class VerificationCodeFilter extends OncePerRequestFilter {

    private final AuthenticationFailureHandler authenticationFailureHandler = new AuthenticationFailHandlerImpl();

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 非登录请求不校验验证码，直接放行
        if (!"/login".equals(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            try {
                //校验验证码
                verificationCode(httpServletRequest);
                //验证码校验通过后，对请求进行放行
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
    }

    public void verificationCode(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String requestCode = httpServletRequest.getParameter(AuthConst.CAPTCHA);
        if (StringUtil.isNullOrUndefined(requestCode)) {
            // 随手清除验证码，不管是失败还是成功，所以客户端应在登录失败时刷新验证码
            throw new VerificationCodeException(StatusCodeEnum.AUTH_CODE_MISSING.getMessage());
        }

        String savedCode = (String) session.getAttribute(AuthConst.CAPTCHA);
        session.removeAttribute(AuthConst.CAPTCHA);
        // 校验不通过抛出异常  这边只能是自定义一个异常  对于RunTimeException没有地方捕获
        if (StringUtil.isEmpty(requestCode) || StringUtil.isEmpty(savedCode) || !requestCode.equals(savedCode)) {
            throw new VerificationCodeException(StatusCodeEnum.AUTH_CODE_ERROR.getMessage());
        }
    }

}