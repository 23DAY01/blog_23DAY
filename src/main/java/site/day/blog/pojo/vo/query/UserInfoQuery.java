package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 用户信息请求
 * @ClassName UserInfoQuery
 * @Author 23DAY
 * @Date 2023/1/29 10:18
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserInfoQuery", description = "用户信息请求")
public class UserInfoQuery {

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(name = "nickname", value = "昵称", dataType = "String")
    private String nickname;

    /**
     * 用户简介
     */
    @ApiModelProperty(name = "intro", value = "介绍", dataType = "String")
    private String intro;

    /**
     * 个人网站
     */
    @ApiModelProperty(name = "website", value = "个人网站", dataType = "String")
    private String website;


}
