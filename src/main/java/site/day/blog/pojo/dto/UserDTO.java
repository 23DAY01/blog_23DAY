package site.day.blog.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @ClassName UserDTO
 * @Author 23DAY
 * @Date 2023/2/2 10:47
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    /**
     * 用户认证id
     */
    private Integer userAuthId;

    /**
     * 用户信息id
     */
    private Integer userInfoId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 是否禁用 0否 1是
     */
    private Boolean isDisabled;

    /**
     * 用户角色
     */
    private List<UserRoleDTO> userRoleDTOList;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

}
