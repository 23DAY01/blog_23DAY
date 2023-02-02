package site.day.blog.strategy.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.day.blog.enums.RoleEnum;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.UserAuthMapper;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.mapper.UserRoleMapper;
import site.day.blog.pojo.domain.UserAuth;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.pojo.domain.UserRole;
import site.day.blog.pojo.dto.SocialLoginDTO;
import site.day.blog.pojo.dto.UserDetail;
import site.day.blog.pojo.dto.UserInfoDTO;
import site.day.blog.service.impl.UserDetailsServiceImpl;
import site.day.blog.strategy.SocialLoginStrategy;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Description
 * @ClassName AbstractSocialLoginStrategy
 * @Author 23DAY
 * @Date 2023/2/2 14:05
 * @Version 1.0
 */
@Service
public abstract class AbstractSocialLoginStrategy implements SocialLoginStrategy {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public UserInfoDTO login(SocialLoginDTO socialLoginDTO) {
        //登录信息
        UserDetail userDetail = new UserDetail();
        //检验token信息
        checkToken(socialLoginDTO);
        //判断是否注册 注册则更新信息 未注册则注册
        UserAuth userAuth = userAuthMapper.selectOne(Wrappers.lambdaQuery(UserAuth.class)
                .eq(UserAuth::getUsername, socialLoginDTO.getOpenId())
                .eq(UserAuth::getLoginType, socialLoginDTO.getLoginType()));
        if (Objects.nonNull(userAuth)) {
            userDetail = getUserDetail(userAuth);
        } else {
            userDetail = saveUserDetail(socialLoginDTO);
        }
        //判断用户是否被禁用
        if (userDetail.getUserAuth().getIsDisabled().equals(true)) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_USER_ACCOUNT_DISABLED);
        }
        //将登录信息放入springSecurity
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities()));
        return userDetail.getUserInfo();
    }

    private UserDetail saveUserDetail(SocialLoginDTO socialLoginDTO) {
        //获取第三方信息
        UserInfoDTO userInfoDTO = getSocialUserInfoDTO(socialLoginDTO);
        //保存用户信息
        UserInfo userInfo = mapStruct.UserInfoDTO2UserInfo(userInfoDTO);
        userInfoMapper.insert(userInfo);
        //保存认证信息
        String ipAddress = WebUtil.getIpAddress(request);
        String ipSource = WebUtil.getIpSource(ipAddress);
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(socialLoginDTO.getOpenId())
                .password(socialLoginDTO.getAccessToken())
                .loginType(socialLoginDTO.getLoginType())
                .lastLoginTime(LocalDateTime.now())
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .build();
        userAuthMapper.insert(userAuth);
        //绑定角色
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        return userDetailsService.convertUserDetail(userAuth, request);
    }


    private UserDetail getUserDetail(UserAuth userAuth) {
        //获取用户ip信息
        String ipAddress = WebUtil.getIpAddress(request);
        String ipSource = WebUtil.getIpSource(ipAddress);
        //更新信息
        userAuthMapper.update(userAuth, Wrappers.lambdaUpdate(UserAuth.class)
                .set(UserAuth::getLastLoginTime, LocalDateTime.now())
                .set(UserAuth::getIpAddress, ipAddress)
                .set(UserAuth::getIpSource, ipSource)
                .eq(UserAuth::getId, userAuth.getId()));
        return userDetailsService.convertUserDetail(userAuth, request);
    }

    public abstract void checkToken(SocialLoginDTO socialLoginDTO);

    public abstract UserInfoDTO getSocialUserInfoDTO(SocialLoginDTO socialLoginDTO);
}
