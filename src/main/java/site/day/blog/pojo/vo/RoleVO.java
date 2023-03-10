package site.day.blog.pojo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @ClassName RoleVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "RoleVO", description = "角色表")
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 是否禁用 0否 1是
     */
    @ApiModelProperty(name = "isDisable", value = "是否禁用 0否 1是", dataType = "Boolean")
    private Boolean isDisable;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

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
