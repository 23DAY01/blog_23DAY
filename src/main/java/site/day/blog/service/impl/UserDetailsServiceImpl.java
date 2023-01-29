package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import eu.bitwalker.useragentutils.UserAgent;
import org.bouncycastle.jcajce.provider.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.mapper.UserAuthMapper;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.pojo.domain.UserAuth;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.pojo.dto.UserAuthDTO;
import site.day.blog.pojo.dto.UserDetail;
import site.day.blog.pojo.dto.UserInfoDTO;
import site.day.blog.service.RoleService;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.RedisUtil;
import site.day.blog.utils.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import static site.day.blog.enums.ZoneEnum.SHANGHAI;


/**
 * 用户详细信息服务
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
//        用户名不能为空
//        if(username.isEmpty()){
//            throw new BadCredentialsException(StatusCodeEnum.AUTH_USERNAME_EMPTY.getMessage());
//        }
        username = username != null ? username.trim() : "";
        // 查询账号是否存在
        UserAuth userAuth = userAuthMapper.selectOne(Wrappers.lambdaQuery(UserAuth.class)
                .eq(UserAuth::getUsername, username));
        if (Objects.isNull(userAuth)) {
            // 这里springSecurity的逻辑有点不合适 因为要隐藏usernameNotFound异常所以抛出了一个badCredential异常 但是对于这个异常的处理必须打印log于是很麻烦
            throw new UsernameNotFoundException(StatusCodeEnum.AUTH_UorP_ERROR.getMessage());
        }
        // 封装登录信息
        return convertUserDetail(userAuth, request);
    }

    /**
     * 封装用户登录信息
     *
     * @param user    用户账号
     * @param request 请求
     * @return 用户登录信息
     */
    public UserDetail convertUserDetail(UserAuth user, HttpServletRequest request) {
        // 查询账号信息
        UserInfo userInfo = userInfoMapper.selectById(user.getUserInfoId());
        // 查询账号角色
        List<String> roleList = roleService.listRolesByUserInfoId(userInfo.getId());
        // 获取设备信息
        String ipAddress = WebUtil.getIpAddress(request);
        String ipSource = WebUtil.getIpSource(ipAddress);
        UserAgent userAgent = WebUtil.getUserAgent(request);

        // pojo转换
        UserInfoDTO userInfoDTO = mapStruct.UserInfo2UserInfoDTO(userInfo);
        UserAuthDTO userAuthDTO = mapStruct.UserAuth2UserAuthDTO(user);

        //填充属性
        userAuthDTO.setOs(userAgent.getOperatingSystem().getName());
        userAuthDTO.setBrowser(userAgent.getBrowser().getName());
        userAuthDTO.setLastLoginTime(LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
        userAuthDTO.setIpAddress(ipAddress);
        userAuthDTO.setIpSource(ipSource);

        // 封装权限集合
        return UserDetail.builder()
                .userInfo(userInfoDTO)
                .userAuth(userAuthDTO)
                .roleList(roleList)
                .build();
    }
}
