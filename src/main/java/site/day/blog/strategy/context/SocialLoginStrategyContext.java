package site.day.blog.strategy.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.day.blog.enums.LoginTypeEnum;
import site.day.blog.pojo.dto.SocialLoginDTO;
import site.day.blog.pojo.dto.UserInfoDTO;
import site.day.blog.strategy.SocialLoginStrategy;

import java.util.Map;

/**
 * @Description
 * @ClassName SocialLoginStrategyContext
 * @Author 23DAY
 * @Date 2023/2/2 14:01
 * @Version 1.0
 */
@Service
public class SocialLoginStrategyContext {

    @Autowired
    private Map<String, SocialLoginStrategy> socialLoginStrategyMap;

    /**
     * @Description qq登录
     * @Author 23DAY
     * @Date 2023/2/2 14:04
     * @Param [java.lang.String, site.day.blog.enums.LoginTypeEnum]
     * @Return site.day.blog.pojo.dto.UserInfoDTO
     **/
    public UserInfoDTO executeLoginStrategy(SocialLoginDTO socialLoginDTO, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(socialLoginDTO);
    }

}
