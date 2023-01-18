package site.day.blog.handler.securityHandler;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import site.day.blog.enums.StatusCodeEnum;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 用户权限管理
 * @ClassName AccessDecisionManagerImpl
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    /**
     * @Description  判断用户是否拥有所访问资源的权限
     * @Author 23DAY
     * @Date 2022/11/2 20:31
     * @Param [org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection<org.springframework.security.access.ConfigAttribute>]
     **/
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 获取用户权限
        List<String> permissionList = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        for (ConfigAttribute item : collection) {
            if (permissionList.contains(item.getAttribute())) {
                return;
            }
        }
        throw new AccessDeniedException(StatusCodeEnum.AUTH_PERMISSION_DENIED.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
