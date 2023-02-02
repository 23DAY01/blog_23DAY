package site.day.blog.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @ClassName QQConfigProperties
 * @Author 23DAY
 * @Date 2023/2/2 15:15
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "login.qq")
public class QQConfigProperties {

    /**
     * qq appId
     */
    private String appId;

    /**
     * 校验token地址
     */
    private String checkTokenUrl;

    /**
     * qq用户信息地址
     */
    private String userInfoUrl;


}
