package site.day.blog.strategy;

import site.day.blog.pojo.dto.SocialLoginDTO;
import site.day.blog.pojo.dto.UserInfoDTO;

/**
 * @Description
 * @ClassName SocialLoginStrategy
 * @Author 23DAY
 * @Date 2023/2/2 14:01
 * @Version 1.0
 */
public interface SocialLoginStrategy {

    UserInfoDTO login(SocialLoginDTO data);

}
