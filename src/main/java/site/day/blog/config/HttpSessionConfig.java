package site.day.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.annotation.Resource;


/**
 * @Description session配置
 * @ClassName HttpSessionConfig
 * @Author 23DAY
 * @Date 2022/9/18 14:46
 * @Version 1.0
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
 
    @Resource
    private FindByIndexNameSessionRepository<? extends Session> sessionRepository;
 
    /**
     * SpringSessionBackedSessionRegistry是Session为Spring Security提供的用于在集群环境中控制并发会话的注册表实现类
     */
    @Bean
    public SpringSessionBackedSessionRegistry<?> springSessionBackedSessionRegistry(){
        return new SpringSessionBackedSessionRegistry<>(sessionRepository);
    }
 
}