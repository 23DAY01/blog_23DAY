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
import site.day.blog.pojo.dto.MenuDTO;

import javax.validation.constraints.NotNull;

/**
 * @ClassName MenuVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "MenuVO", description = "菜单表")
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(name = "menuName", value = "菜单名称", dataType = "String")
    private String menuName;

    /**
     * 菜单路径
     */
    @ApiModelProperty(name = "menuPath", value = "菜单路径", dataType = "String")
    private String menuPath;

    /**
     * 组件
     */
    @ApiModelProperty(name = "component", value = "组件", dataType = "String")
    private String component;

    /**
     * 菜单icon
     */
    @ApiModelProperty(name = "icon", value = "菜单icon", dataType = "String")
    private String icon;

    /**
     * 排序级别
     */
    @ApiModelProperty(name = "orderNum", value = "排序级别", dataType = "Integer")
    private Integer orderNum;

    /**
     * 是否隐藏  0否1是
     */
    @ApiModelProperty(name = "isHidden", value = "是否隐藏  0否1是", dataType = "Boolean")
    private Boolean isHidden;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    /**
     * 子菜单
     */
    @ApiModelProperty(name = "childMenuList", value = "子菜单", dataType = "List<MenuVO>")
    private List<MenuVO> childMenuList;


}
