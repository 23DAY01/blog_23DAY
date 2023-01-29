package site.day.blog.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName UserAuthDTO
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserAuthDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户信息id
     */
    private Integer userInfoId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 用户操作系统
     */
    private String os;

    /**
     * 用户浏览器
     */
    private String browser;

    /**
     * 是否禁用 0否 1是
     */
    private Boolean isDisabled;


}
