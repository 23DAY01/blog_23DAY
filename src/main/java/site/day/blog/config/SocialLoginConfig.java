package site.day.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @ClassName SocialLoginConfig
 * @Author 23DAY
 * @Date 2023/2/2 15:57
 * @Version 1.0
 */
@Configuration
public class SocialLoginConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
