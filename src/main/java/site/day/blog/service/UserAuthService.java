package site.day.blog.service;

import site.day.blog.pojo.domain.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.vo.query.UserAuthQuery;

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
}
