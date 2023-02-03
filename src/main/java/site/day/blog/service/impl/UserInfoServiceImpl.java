package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.enums.FilePathEnum;
import site.day.blog.mapper.UserRoleMapper;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.pojo.domain.UserRole;
import site.day.blog.pojo.dto.UserDTO;
import site.day.blog.pojo.dto.UserDetail;
import site.day.blog.pojo.vo.query.UserRoleQuery;
import site.day.blog.pojo.vo.query.EmailQuery;
import site.day.blog.pojo.vo.query.UserInfoQuery;
import site.day.blog.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.service.UserRoleService;
import site.day.blog.strategy.context.UploadStrategyContext;
import site.day.blog.utils.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description UserInfo服务实现类
 * @ClassName UserInfoServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private UserAuthServiceImpl userAuthService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * @Description 更新用户信息
     * @Author 23DAY
     * @Date 2023/1/29 10:31
     * @Param [site.day.blog.pojo.vo.query.UserInfoQuery]
     * @Return void
     **/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserInfo(UserInfoQuery user) {
        UserInfo userInfo = UserInfo.builder()
                .id(AuthUtil.getLoginUser().getUserInfo().getId())
                .nickname(user.getNickname())
                .intro(user.getIntro())
                .website(user.getWebsite())
                .build();
        userInfoMapper.updateById(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateUserAvatar(MultipartFile file) {
        //上传图片
        String url = uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.AVATAR.getPath());
        //更新用户信息
        UserInfo userInfo = UserInfo.builder()
                .id(AuthUtil.getLoginUser().getUserInfo().getId())
                .avatar(url)
                .build();
        userInfoMapper.updateById(userInfo);
        return url;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserEmail(EmailQuery emailQuery) {
        userAuthService.checkCodeValidity(emailQuery.getEmail(), emailQuery.getCode());
        UserInfo userInfo = UserInfo.builder()
                .id(AuthUtil.getLoginUser().getUserInfo().getId())
                .email(emailQuery.getEmail())
                .build();
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public void updateUserRole(UserRoleQuery query) {
        //删除旧关系
        userRoleMapper.delete(Wrappers.lambdaQuery(UserRole.class)
                .eq(UserRole::getUserId, query.getUserInfoId()));
        //添加新关系
        List<UserRole> userRoleList = query.getRoleIdList()
                .stream()
                .map(roleId -> UserRole.builder()
                        .roleId(roleId)
                        .userId(query.getUserInfoId())
                        .build())
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public List<UserDTO> getOnlineUsers() {
        List<UserDTO> userDTOList = sessionRegistry.getAllPrincipals()
                .stream()
                .filter(principal -> sessionRegistry.getAllSessions(principal, false).size() > 0)
                .map(principal -> (UserDetail) principal)
                .map(userDetail -> mapStruct.UserInfoDTO4UserAuthDTO2UserDTO(userDetail.getUserInfo(), userDetail.getUserAuth()))
                .sorted(Comparator.comparing(UserDTO::getLastLoginTime).reversed())
                .collect(Collectors.toList());

        int fromIndex = Math.toIntExact(PageUtil.getCurrent());
        int size = Math.toIntExact(PageUtil.getSize());
        int toIndex = userDTOList.size() - fromIndex > size ? fromIndex + size : userDTOList.size();
        PageUtil.setTotal((long) userDTOList.size());
        userDTOList = userDTOList.subList(fromIndex, toIndex);
        return userDTOList;
    }
}
