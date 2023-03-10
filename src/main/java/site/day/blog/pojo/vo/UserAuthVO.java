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

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

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


}
