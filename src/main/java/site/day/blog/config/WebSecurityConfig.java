package site.day.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.cors.CorsConfigurationSource;
import site.day.blog.constant.AuthConst;
import site.day.blog.filter.LoginAuthenticationFilter;
import site.day.blog.filter.RepeatableFilter;
import site.day.blog.handler.securityHandler.*;
import site.day.blog.handler.securityHandler.InvalidSessionStrategyImpl;
import site.day.blog.service.impl.UserDetailsServiceImpl;

import javax.annotation.Resource;


/**
 * @Description springSecurity缓存配置
 * @ClassName WebSecurityConfig
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * ------------------------------------------------------------------
     * formLogin配置
     * <p>
     * ------------------------------------------------------------------
     */

    // 重写loadUserByUsername
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    //  证异常处理
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    // 授权异常处理
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandler;

    // 认证成功处理器
    @Resource
    @Lazy
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    // 认证失败处理器
    @Resource
    private AuthenticationFailHandlerImpl authenticationFailHandler;

    // 登出成功处理器
    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    // 自定义接口拦截规则
    @Resource
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    // 自定义权限提取
    @Resource
    private AccessDecisionManager accessDecisionManager;

    /**
     * ------------------------------------------------------------------
     * misc配置
     * <p>
     * ------------------------------------------------------------------
     */

    // 密码加密策略
    @Resource
    private PasswordEncoder passwordEncoder;


    // 需要添加到自定义authenticationFilter中
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // remember-me service
    @Resource
    private TokenBasedRememberMeServices tokenBasedRememberMeServices;

    // 跨域访问
    @Resource
    private CorsConfigurationSource corsConfigurationSource;


    /**
     * ------------------------------------------------------------------
     * 自定义配置
     * <p>
     * ------------------------------------------------------------------
     */

    // 重复使用InputStream
    @Resource
    private RepeatableFilter repeatableFilter;

    // 自定义密码比对
    @Resource
    private DaoAuthenticationProviderImpl daoAuthenticationProvider;

//    // 图形过滤器
//    @Resource
//    private VerificationCodeFilter verificationCodeFilter;

    // 自定义登录过滤器
    @Bean
    public LoginAuthenticationFilter LoginAuthenticationFilter() throws Exception {
        //配置完loginFilter后 fromLogin的所有配置全部失效 应再次配置到loginFilter中
        LoginAuthenticationFilter loginFilter = new LoginAuthenticationFilter();
        //
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        // 登录接口
        loginFilter.setFilterProcessesUrl("/login");
        // 认证成功处理器
        loginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 认证失败处理器
        loginFilter.setAuthenticationFailureHandler(authenticationFailHandler);
        // remember-me
        loginFilter.setRememberMeServices(tokenBasedRememberMeServices);
        // session
        loginFilter.setSessionAuthenticationStrategy(compositeSessionAuthenticationStrategy);

//        loginFilter.setAuthenticationDetailsSource();
//        loginFilter.setSecurityContextRepository();
        return loginFilter;
    }


    /**
     * ------------------------------------------------------------------
     * session配置
     * <p>
     * ------------------------------------------------------------------
     */


    // redis存储session 默认在jvm内存中存储
    @Resource
    @Lazy
    private SpringSessionBackedSessionRegistry<?> redisSessionRegistry;

    // 可以通过监听session的创建及销毁事件，来及时的清理session记录
    @Resource
    private HttpSessionEventPublisher httpSessionEventPublisher;

    // session错误策略
    @Resource
    private InvalidSessionStrategyImpl invalidSessionStrategy;

    // session过期策略
    @Resource
    private SessionInformationExpiredStrategyImpl sessionInformationExpiredStrategy;

    // session并发策略：处理
    @Resource
    private SessionFixationProtectionStrategy sessionFixationProtectionStrategy;

    // session并发处理策略
    @Resource
    private ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy;

    // session登录处理策略
    @Resource
    private ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy;

    // 整合session策略
    @Resource
    private CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy;


    /**
     * @Description 配置权限
     * 在这里的配置都会被配置到usernamePasswordFilter中 所以如果重写了该Filter就需要自行注入formLogin和session
     * @Author 23DAY
     * @Date 2022/10/14 20:53
     * @Param [org.springframework.security.config.annotation.web.builders.HttpSecurity]
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置登录注销路径与处理器
        http
//                配置表单登录
                .formLogin()
                //登录url
                .loginProcessingUrl("/login")
                //验证成功与失败处理器
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler).and()


//                登出
                .logout()
                //登出url
                .logoutUrl("/logout")
                //登出成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                //session失效
                .invalidateHttpSession(true)
                //清除认证信息
                .clearAuthentication(true);


        // 配置路由权限信息
        http
//                自配置认证规则：动态权限
                .authorizeRequests()
                .withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                                fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                                fsi.setAccessDecisionManager(accessDecisionManager);
                                return fsi;
                            }
                        })
                //对于所有url都放行，在accessDecisionManager中进行认证
                .anyRequest().permitAll().and()


//                记住我功能
                .rememberMe()
                .key(AuthConst.REMEMBER_ME_KEY)
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(AuthConst.TOKEN_VALIDITY_SECONDS).and()

//                springSecurity的跨域配置
                .cors()
                .configurationSource(corsConfigurationSource).and()

//                关闭跨站请求防护
                .csrf().disable()

//                异常处理
                .exceptionHandling()
//                认证异常处理
                .authenticationEntryPoint(authenticationEntryPoint)
//                授权异常处理
                .accessDeniedHandler(accessDeniedHandler).and()

//                session配置
                .sessionManagement()
                //none: 该策略对会话不做任何变动，登录之后会沿用旧的session;
                //newSession: 用户登录后会创建一个新的session；
                //migrateSession: 默认策略，用户登录后创建一个新的session，并将旧session中的数据复制过来；
                //changeSessionId: 表示 session 不变，不会创建新的session，但是会修改 sessionId，内部使用由Servlet容器提供的会话固定保护。
                .sessionFixation()
                .changeSessionId()
                //session过期策略
                .invalidSessionStrategy(invalidSessionStrategy)
                //session并发
                .maximumSessions(1)
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                //session集群会话处理
                .sessionRegistry(redisSessionRegistry);


//        添加过滤器
        http
//                // 自定义登录过滤器
                .addFilterAt(LoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                // 图形验证码
                //.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
