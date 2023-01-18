package site.day.blog.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.day.blog.utils.RedisUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description Jwt过滤器
 * @ClassName JwtAuthenticationTokenFilter
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        //获取token
//        String token = request.getHeader("token");
//        if (!StringUtils.hasText(token)) {
//            //放行
//            filterChain.doFilter(request, response);
//            return;
//        }
//        //解析token
//        String userid;
//        try {
//            Claims claims = JwtUtil.parseJWT(token);
//            userid = claims.getSubject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("token非法");
//        }
//        //从redis中获取用户信息
//        String redisKey = RedisPrefixConst.USER_LOGIN + userid;
//        UserDetail userDetail = (UserDetail) redisUtil.get(redisKey);
//        if (ObjectUtil.isNull(userDetail)) {
//            throw new BusinessException(StatusCode.CLIENT.AUTH_NO_LOGIN.getCode());
//        }
//        //存入SecurityContextHolder
//        //TODO 获取权限信息封装到Authentication中
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetail, null, null);
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        //放行
        filterChain.doFilter(request, response);
    }

}