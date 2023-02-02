package site.day.blog.strategy.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.day.blog.config.property.QQConfigProperties;
import site.day.blog.constant.SocialLoginConst;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.pojo.dto.QQTokenDTO;
import site.day.blog.pojo.dto.QQUserInfoDTO;
import site.day.blog.pojo.dto.SocialLoginDTO;
import site.day.blog.pojo.dto.UserInfoDTO;
import site.day.blog.strategy.service.AbstractSocialLoginStrategy;
import site.day.blog.utils.JsonUtil;
import site.day.blog.utils.RedisUtil;
import site.day.blog.utils.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

import static site.day.blog.constant.SocialLoginConst.*;

/**
 * @Description
 * @ClassName QQLoginStrategyImpl
 * @Author 23DAY
 * @Date 2023/2/2 15:14
 * @Version 1.0
 */
@Service
public class QQLoginStrategyImpl extends AbstractSocialLoginStrategy {

    @Autowired
    private QQConfigProperties qqConfigProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Description 检验token
     * @Author 23DAY
     * @Date 2023/2/2 15:42
     * @Param [site.day.blog.pojo.dto.SocialLoginDTO]
     * @Return void
     **/
    @Override
    public void checkToken(SocialLoginDTO socialLoginDTO) {
        HashMap<Object, Object> qqLoginData = new HashMap<>(1);
        qqLoginData.put(SocialLoginConst.ACCESS_TOKEN, socialLoginDTO.getAccessToken());
        String result = restTemplate.getForObject(qqConfigProperties.getCheckTokenUrl(), String.class, qqLoginData);
        try {
            QQTokenDTO qqTokenDTO = (QQTokenDTO) JsonUtil.String2Object(StringUtil.getBracketsContent(Objects.requireNonNull(result)), QQTokenDTO.class);
            //比较openId是否一致
            if (!socialLoginDTO.getOpenId().equals(qqTokenDTO.getOpenId())) {
                throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_QQ_ERROR);
            }
        } catch (Exception e) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_QQ_ERROR);
        }
    }

    /**
     * @Description 获取用户信息
     * @Author 23DAY
     * @Date 2023/2/2 15:42
     * @Param [site.day.blog.pojo.dto.SocialLoginDTO]
     * @Return site.day.blog.pojo.dto.UserInfoDTO
     **/
    @SneakyThrows
    @Override
    public UserInfoDTO getSocialUserInfoDTO(SocialLoginDTO socialLoginDTO) {
        HashMap<String, String> loginData = new HashMap<>(3);
        loginData.put(QQ_OPEN_ID, socialLoginDTO.getOpenId());
        loginData.put(ACCESS_TOKEN, socialLoginDTO.getAccessToken());
        loginData.put(OAUTH_CONSUMER_KEY, qqConfigProperties.getAppId());
        //获取qq信息
        QQUserInfoDTO qqUserInfoDTO = (QQUserInfoDTO) JsonUtil.String2Object(restTemplate.getForObject(qqConfigProperties.getUserInfoUrl(), String.class, loginData), QQUserInfoDTO.class);
        //转换为dto
        return UserInfoDTO.builder()
                .avatar(qqUserInfoDTO.getFigureurl_qq_q())
                .nickname(qqUserInfoDTO.getNickname())
                .build();
    }
}
