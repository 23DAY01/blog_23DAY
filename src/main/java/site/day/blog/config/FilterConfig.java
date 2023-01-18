package site.day.blog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.day.blog.filter.RepeatableFilter;



/**
 * @Description 过滤器配置
 * @ClassName FilterConfig
 * @Author 23DAY
 * @Date 2022/9/18 14:46
 * @Version 1.0
 */
@Configuration
public class FilterConfig
{
    @Bean
    public FilterRegistrationBean repeatableFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
}
