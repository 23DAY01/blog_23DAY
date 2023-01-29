package site.day.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.enums.FilePathEnum;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.pojo.vo.query.EmailQuery;
import site.day.blog.pojo.vo.query.UserInfoQuery;
import site.day.blog.service.UserAuthService;
import site.day.blog.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.strategy.context.UploadStrategyContext;
import site.day.blog.utils.AuthUtil;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.RedisUtil;
import site.day.blog.utils.WebUtil;

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
}
