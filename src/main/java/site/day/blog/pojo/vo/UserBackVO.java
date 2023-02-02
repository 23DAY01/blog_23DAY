package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import site.day.blog.pojo.dto.UserRoleDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 后台用户
 * @ClassName UserBackVO
 * @Author 23DAY
 * @Date 2023/2/2 10:15
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserBackVO", description = "后台用户")
public class UserBackVO {

    /**
     * 用户认证id
     */
    @ApiModelProperty(name = "userAuthid", value = "用户认证id", dataType = "Integer")
    private Integer userAuthid;

    /**
     * 用户信息id
     */
    @ApiModelProperty(name = "userInfoId", value = "用户信息id", dataType = "Integer")
    private Integer userInfoId;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "username", value = "用户名", dataType = "String")
    private String username;

    /**
     * 登录类型
     */
    @ApiModelProperty(name = "loginType", value = "登录类型", dataType = "Integer")
    private Integer loginType;

    /**
     * ip地址
     */
    @ApiModelProperty(name = "ipAddress", value = "ip地址", dataType = "String")
    private String ipAddress;

    /**
     * ip来源
     */
    @ApiModelProperty(name = "ipSource", value = "ip来源", dataType = "String")
    private String ipSource;

    /**
     * 上次登录时间
     */
    @ApiModelProperty(name = "lastLoginTime", value = "上次登录时间", dataType = "LocalDateTime")
    private LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱", dataType = "String")
    private String email;

    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "String")
    private String avatar;

    /**
     * 是否禁用 0否 1是
     */
    @ApiModelProperty(name = "isDisabled", value = "是否禁用 0否 1是", dataType = "Boolean")
    private Boolean isDisabled;

    /**
     * 用户角色
     */
    private List<UserRoleVO> userRoleDTOList;

}
