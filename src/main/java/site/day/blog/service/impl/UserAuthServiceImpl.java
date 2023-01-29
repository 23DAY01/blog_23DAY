package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import site.day.blog.enums.LoginTypeEnum;
import site.day.blog.enums.RoleEnum;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.mapper.UserRoleMapper;
import site.day.blog.mapper.WebsiteConfigMapper;
import site.day.blog.pojo.domain.UserAuth;
import site.day.blog.mapper.UserAuthMapper;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.pojo.domain.UserRole;
import site.day.blog.pojo.dto.EmailDTO;
import site.day.blog.pojo.vo.query.UserAuthQuery;
import site.day.blog.service.UserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.CommonUtil;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.Objects;

import static site.day.blog.constant.CommonConst.DEFAULT_NICKNAME;
import static site.day.blog.constant.CommonConst.DEFAULT_WEBSITE_CONFIG_ID;
import static site.day.blog.constant.RedisConst.*;

/**
 * @Description UserAuth服务实现类
 * @ClassName UserAuthServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private WebsiteConfigMapper websiteConfigMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * @Description 邮箱验证码
     * @Author 23DAY
     * @Date 2023/1/29 0:14
     * @Param [java.lang.String]
     * @Return void
     **/
    @Override
    public void sendEmailCode(String email) {
        String code = CommonUtil.getRandomCode();
        EmailDTO emailDTO = EmailDTO.builder()
                .email(email)
                .subject("邮箱验证码")
                .content("邮箱验证码为 " + code + " 有效期15分钟")
                .build();
        //TODO 整合rabbitmq
        redisUtil.set(EMAIL_USER_CODE + email, code, CODE_EXPIRE_TIME);
    }

    /**
     * @Description 用户注册
     * @Author 23DAY
     * @Date 2023/1/29 0:44
     * @Param [site.day.blog.pojo.vo.query.UserAuthQuery]
     * @Return void
     **/
    @Override
    public void register(UserAuthQuery user) {
        //检验合法性
        checkCodeValidity(user.getUsername(), user.getCode());
        checkUserExist(user.getUsername(), false);
        //封装
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(DEFAULT_NICKNAME + IdWorker.getId())
                .avatar(websiteConfigMapper.selectById(DEFAULT_WEBSITE_CONFIG_ID).getUserAvatar())
                .build();
        userInfoMapper.insert(userInfo);
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .loginType(LoginTypeEnum.ACCOUNT.getType())
                .build();
        userAuthMapper.insert(userAuth);
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);

    }

    /**
     * @Description 修改密码
     * @Author 23DAY
     * @Date 2023/1/29 10:00
     * @Param [site.day.blog.pojo.vo.query.UserAuthQuery]
     * @Return void
     **/
    @Override
    public void updatePassword(UserAuthQuery user) {
        //检验合法性
        checkCodeValidity(user.getUsername(), user.getCode());
        checkUserExist(user.getUsername(), true);
        //修改密码
        userAuthMapper.update(new UserAuth(), Wrappers.lambdaUpdate(UserAuth.class)
                .set(UserAuth::getPassword, passwordEncoder.encode(user.getPassword()))
                .eq(UserAuth::getUsername, user.getUsername()));
    }

    /**
     * @Description 检验用户名和校验码的合法性
     * @Author 23DAY
     * @Date 2023/1/29 0:45
     * @Param [java.lang.String, java.lang.String]
     * @Return void
     **/
    private void checkCodeValidity(String username, String code) {
        //检验邮箱校验码
        if (!code.equals(redisUtil.get(USER_CODE_KEY + username))) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_CODE_ERROR);
        }
    }

    /**
     * @Description 检查用户是否存在  flag=true是存在
     * @Author 23DAY
     * @Date 2023/1/29 9:49
     * @Param [java.lang.String, java.lang.Boolean]
     * @Return void
     **/
    private void checkUserExist(String username, Boolean flag) {
        UserAuth userAuth = userAuthMapper.selectOne(Wrappers.lambdaQuery(UserAuth.class)
                .eq(UserAuth::getUsername, username));
        if (!flag && Objects.nonNull(userAuth)) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_USER_USERNAME_REPEAT);
        } else if (flag && Objects.isNull(userAuth)) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_USERNAME_NOT_FOUND);
        }
    }

    //TODO oauth2登录


}
