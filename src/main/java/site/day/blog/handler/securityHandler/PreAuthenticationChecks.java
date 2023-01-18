package site.day.blog.handler.securityHandler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import site.day.blog.enums.StatusCodeEnum;

/**
 * @Description 用户认证后检查
 * @ClassName PreAuthenticationChecks
 * @Author 23DAY
 * @Date 2022/10/16 15:01
 * @Version 1.0
 */
@Log4j2
@Component
public class PreAuthenticationChecks implements UserDetailsChecker {

    private PreAuthenticationChecks() {
    }

    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            log.debug("Failed to authenticate since user account is locked");
            throw new LockedException(StatusCodeEnum.AUTH_USER_ACCOUNT_LOCKED.getMessage());
        } else if (!user.isEnabled()) {
            log.debug("Failed to authenticate since user account is disabled");
            throw new DisabledException(StatusCodeEnum.AUTH_USER_ACCOUNT_DISABLED.getMessage());
        } else if (!user.isAccountNonExpired()) {
            log.debug("Failed to authenticate since user account has expired");
            throw new AccountExpiredException(StatusCodeEnum.AUTH_USER_ACCOUNT_EXPIRED.getMessage());
        }
    }
}

