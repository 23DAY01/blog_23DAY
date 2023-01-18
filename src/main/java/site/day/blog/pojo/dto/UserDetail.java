package site.day.blog.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static site.day.blog.constant.CommonConst.FALSE;


@Data
@Accessors(chain = true)
@ApiModel(value = "UserDetail", description = "")
public class UserDetail implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;
    //    用户认证
    private UserAuthDTO userAuthDto;

    //    用户信息
    private UserInfoDTO userInfoDTO;

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
        return this.userAuthDto.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userAuthDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userAuthDto.getIsDisabled() == FALSE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userAuthDto.getIsDisabled() == FALSE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userAuthDto.getIsDisabled() == FALSE;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserDetail that = (UserDetail) o;
//        return Objects.equals(userAuthDto.getUsername(), that.userAuthDto.getUsername());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userAuthDto.getUsername());
//    }
//
//    @Override
//    public String toString() {
//        return userAuthDto.getUsername();
//    }
}
