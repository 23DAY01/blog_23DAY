package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 修改密码
 * @ClassName UserPasswordQuery
 * @Author 23DAY
 * @Date 2023/2/2 13:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserPasswordQuery", description = "修改密码")
public class UserPasswordQuery {

    /**
     * 旧密码
     */
    @NotBlank
    @ApiModelProperty(name = "oldPassword",value = "旧密码",dataType = "String")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank
    @ApiModelProperty(name = "newPassword",value = "新密码",dataType = "String")
    private String newPassword;

}
