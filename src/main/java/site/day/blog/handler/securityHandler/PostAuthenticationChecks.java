package site.day.blog.handler.securityHandler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import site.day.blog.enums.StatusCodeEnum;


/**
 * @Description 用户认证后检查
 * @ClassName PostAuthenticationChecks
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Log4j2
@Component
public class PostAuthenticationChecks implements UserDetailsChecker {
    private PostAuthenticationChecks() {
    }

    public void check(UserDetails user) {
        if (!user.isCredentialsNonExpired()) {
            log.debug("Failed to authenticate since user account credentials have expired");
            throw new CredentialsExpiredException(StatusCodeEnum.AUTH_USER_CREDENTIALS_EXPIRED.getMessage());
        }
    }
}