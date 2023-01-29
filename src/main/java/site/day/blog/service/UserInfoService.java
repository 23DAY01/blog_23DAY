package site.day.blog.service;

import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.vo.query.EmailQuery;
import site.day.blog.pojo.vo.query.UserInfoQuery;

/**
 * @Description UserInfo服务类
 * @ClassName UserInfoService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface UserInfoService extends IService<UserInfo> {

    void updateUserInfo(UserInfoQuery userInfoQuery);

    String updateUserAvatar(MultipartFile file);

    void saveUserEmail(EmailQuery emailQuery);
}
