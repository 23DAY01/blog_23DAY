package site.day.blog.handler.securityHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import site.day.blog.pojo.domain.UserAuth;
import site.day.blog.pojo.dto.UserAuthDTO;
import site.day.blog.pojo.dto.UserDetail;
import site.day.blog.service.UserAuthService;
import site.day.blog.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description 认证成功处理器
 * @ClassName AuthenticationSuccessHandlerImpl
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    @Lazy
    private UserAuthService userAuthService;

    /**
     * TODO 获取登录信息  更新用户信息
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 返回登录信息
        UserDetail loginUser = AuthUtil.getLoginUser();
        //更新用户ip，最近登录时间
        updateUserInfo();
//
//        System.out.println(httpServletRequest.getSession().getMaxInactiveInterval());
//        System.out.println(httpServletRequest.getSession().getLastAccessedTime());
//        System.out.println(httpServletRequest.getSession().getCreationTime());
//        System.out.println(httpServletRequest.getSession().getId());

        WebUtil.render(httpServletResponse, JsonUtil.Object2String(ResponseAPI.success(loginUser.getUserInfoDTO())));
    }

    /**
     * @Description 更新用户信息
     * @Author 23DAY
     * @Date 2022/11/2 20:02
     * @Param []
     **/
    @Async
    public void updateUserInfo() {
        UserDetail loginUser = AuthUtil.getLoginUser();
        UserAuthDTO userAuthDto = loginUser.getUserAuthDto();
        UserAuth userAuth = mapStruct.UserAuthDTO2UserAuth(userAuthDto);
        userAuthService.updateById(userAuth);
    }

}
