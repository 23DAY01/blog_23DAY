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
 * @ClassName RoleMenuVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "RoleMenuVO", description = "角色菜单表")
public class RoleMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @NotNull(message = "roleId不能为空")
    @ApiModelProperty(name = "roleId", value = "角色id", dataType = "Integer")
    private Integer roleId;

    @NotNull(message = "menuId不能为空")
    @ApiModelProperty(name = "menuId", value = "菜单id", dataType = "Integer")
    private Integer menuId;

    @NotNull(message = "deleted不能为空")
    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @NotNull(message = "updateTime不能为空")
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
