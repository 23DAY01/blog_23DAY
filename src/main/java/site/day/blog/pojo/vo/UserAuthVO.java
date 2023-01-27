package site.day.blog.pojo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserAuthVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserAuthVO", description = "用户权限表")
public class UserAuthVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "userInfoId", value = "用户信息id", dataType = "Integer")
    private Integer userInfoId;

    @ApiModelProperty(name = "username", value = "用户名", dataType = "String")
    private String username;

    @ApiModelProperty(name = "password", value = "密码", dataType = "String")
    private String password;

    @ApiModelProperty(name = "loginType", value = "登录类型", dataType = "Integer")
    private Integer loginType;

    @ApiModelProperty(name = "ipAddress", value = "ip地址", dataType = "String")
    private String ipAddress;

    @ApiModelProperty(name = "ipSource", value = "ip来源", dataType = "String")
    private String ipSource;

    @ApiModelProperty(name = "lastLoginTime", value = "上次登录时间", dataType = "LocalDateTime")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
