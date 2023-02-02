package site.day.blog.service;

import site.day.blog.pojo.domain.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.UserAreaDTO;
import site.day.blog.pojo.dto.UserDTO;
import site.day.blog.pojo.dto.UserInfoDTO;
import site.day.blog.pojo.vo.query.UserAuthQuery;
import site.day.blog.pojo.vo.query.UserPasswordQuery;
import site.day.blog.pojo.vo.query.UserSocialLoginQuery;
import site.day.blog.pojo.vo.query.UserStatusQuery;

import java.util.List;

/**
 * @Description UserAuth服务类
 * @ClassName UserAuthService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface UserAuthService extends IService<UserAuth> {

    void sendEmailCode(String email);

    void register(UserAuthQuery userAuthQuery);

    void updatePassword(UserAuthQuery userAuthQuery);

    List<UserAreaDTO> getUserArea(Integer type);

    List<UserDTO> getBackUser(Integer type);

    void updateUserPassword(UserPasswordQuery userPasswordQuery);

    UserInfoDTO loginByQQ(UserSocialLoginQuery userSocialLoginQuery);

    void updateUserStatus(UserStatusQuery userStatusQuery);
}
