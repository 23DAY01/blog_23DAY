package site.day.blog.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static site.day.blog.constant.CommonConst.FALSE;


/**
 * @ClassName UserDetail
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserDetail", description = "")
@Builder
public class UserDetail implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;
    //    用户认证
    private UserAuthDTO userAuth;

    //    用户信息
    private UserInfoDTO userInfo;

    //    权限列表
    private List<String> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.userAuth.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userAuth.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userAuth.getIsDisabled() == FALSE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userAuth.getIsDisabled() == FALSE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userAuth.getIsDisabled() == FALSE;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
