package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 添加角色
 * @ClassName RoleSaveQuery
 * @Author 23DAY
 * @Date 2023/2/1 22:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RoleSaveQuery", description = "添加角色")
public class RoleSaveQuery {

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", value = "主键id", dataType = "Integer")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(name = "roleName", value = "角色名称", dataType = "String")
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(name = "roleLabel", value = "角色描述", dataType = "String")
    private String roleLabel;

    /**
     * 资源id列表
     */
    @ApiModelProperty(name = "resourceIdList", value = "资源id列表", dataType = "List<Integer>")
    private List<Integer> resourceIdList;

    /**
     * 菜单id列表
     */
    @ApiModelProperty(name = "menuIdList", value = "菜单id列表", dataType = "List<Integer>")
    private List<Integer> menuIdList;

}
