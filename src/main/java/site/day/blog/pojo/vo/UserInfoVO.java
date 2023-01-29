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
 * @ClassName UserInfoVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserInfoVO", description = "用户信息表")
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "email", value = "邮箱", dataType = "String")
    private String email;

    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "String")
    private String avatar;

    @ApiModelProperty(name = "intro", value = "用户简介", dataType = "String")
    private String intro;

    @ApiModelProperty(name = "website", value = "个人网站", dataType = "String")
    private String website;

    @ApiModelProperty(name = "isDisabled", value = "是否禁用 0否 1是", dataType = "Boolean")
    private Boolean isDisabled;

    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;



}
