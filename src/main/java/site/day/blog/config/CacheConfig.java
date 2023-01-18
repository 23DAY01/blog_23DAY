package site.day.blog.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @Description spring缓存配置
 * @ClassName CacheConfig
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    private Map<String, RedisCacheConfiguration> TTlParam = new HashMap<>();

    /**
     * @Description key生成策略
     * @Author 23DAY
     * @Date 2022/10/14 20:46
     * @Param []
     * @return org.springframework.cache.interceptor.KeyGenerator
     **/
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String t = target.getClass().getName();
            String m = method.getName();
            return String.format("%s:%s(%s)", t, m, CollectionUtils.arrayToList(params));
        };
    }

    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(Objects.requireNonNull(cacheManager()));
    }

    @Override
    public CacheErrorHandler errorHandler() {
        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
        return new SimpleCacheErrorHandler();
    }


    /**
     * @Description
     * @Author 23DAY
     * @Date 2022/9/21 20:19
     * @Param []
     * @return org.springframework.cache.CacheManager
     **/
    @Override
    public CacheManager cacheManager() {

        //针对java8下LocalDateTime反序列化的处理
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.registerModule(new JavaTimeModule());
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.
                defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .computePrefixWith(name -> name + ":")
                // 设置key采用String的序列化方式
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(StringRedisSerializer.UTF_8))
                //当value为null时不进行缓存
                .disableCachingNullValues()
                //设置value序列化方式采用jackson方式序列化
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(genericJackson2JsonRedisSerializer));

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                //事务感知
                .transactionAware()
                //.withInitialCacheConfigurations(CacheNameTimeConstant.initConfigs(redisCacheConfiguration))
                //定制过期时间
                .withInitialCacheConfigurations(TTlParam)
                .build();
    }

    //定制过期时间
    public void initTTLParam() {

        //可以通过一个常量类CacheNameTimeConstant来定制 这里就先这么写了
        //.withInitialCacheConfigurations(CacheNameTimeConstant.initConfigs(redisCacheConfiguration))

//        TTlParam.put("users", RedisCacheConfiguration
//                .defaultCacheConfig().entryTtl(Duration.ofSeconds(60000)));
    }

}
