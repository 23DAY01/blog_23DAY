package site.day.blog.handler.securityHandler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import site.day.blog.constant.AuthConst;

import javax.annotation.Resource;


/**
 * @Description 自定义密码比对
 * @ClassName MyAuthenticationProvider
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class DaoAuthenticationProviderImpl extends DaoAuthenticationProvider {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(AuthConst.StatusMessage.AUTH_UorP_ERROR);
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Failed to authenticate since password does not match stored value");
                throw new BadCredentialsException(AuthConst.StatusMessage.AUTH_UorP_ERROR);
            }
        }
    }
}
