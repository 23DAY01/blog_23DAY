package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description 修改用户角色
 * @ClassName UserRoleQuery
 * @Author 23DAY
 * @Date 2023/2/2 18:12
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserRoleQuery", description = "修改用户角色")
public class UserRoleQuery {

    /**
     * 用户信息id
     */
    @ApiModelProperty(name = "userInfoId", value = "用户信息id", dataType = "Integer")
    @NotNull
    private Integer userInfoId;

    /**
     * 角色id
     */
    @ApiModelProperty(name = "roleIdList", value = "角色id", dataType = "List<Integer>")
    @NotEmpty
    private List<Integer> roleIdList;

}
