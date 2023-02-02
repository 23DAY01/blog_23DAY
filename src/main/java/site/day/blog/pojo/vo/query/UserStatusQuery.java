package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description 修改用户状态
 * @ClassName UserStatusQuery
 * @Author 23DAY
 * @Date 2023/2/2 18:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserStatusQuery", description = "修改用户状态")
public class UserStatusQuery {

    /**
     * 用户信息id
     */
    @ApiModelProperty(name = "idList",value = "用户信息id",dataType = "List<Integer>")
    @NotEmpty
    private List<Integer> idList;

    /**
     * 禁用状态
     */
    @ApiModelProperty(name = "isDisabled",value = "禁用状态",dataType = "Boolean")
    @NotNull
    private Boolean isDisabled;


}
