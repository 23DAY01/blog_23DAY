package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description qq登录
 * @ClassName UserSocialAuthQuery
 * @Author 23DAY
 * @Date 2023/2/2 13:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserSocialAuthQuery", description = "第三方登录")
public class UserSocialLoginQuery {

    /**
     * openId
     */
    @NotBlank
    @ApiModelProperty(name = "openId", value = "openId", dataType = "String")
    private String openId;


    /**
     * accessToken
     */
    @NotBlank
    @ApiModelProperty(name = "accessToken", value = "accessToken", dataType = "String")
    private String accessToken;

}
