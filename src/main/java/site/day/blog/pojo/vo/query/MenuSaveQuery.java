package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 添加菜单
 * @ClassName MenuSaveQuery
 * @Author 23DAY
 * @Date 2023/1/31 23:15
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MenuSaveQuery", description = "添加菜单")
public class MenuSaveQuery {

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
     * 父菜单id
     */
    @ApiModelProperty(name = "parentId", value = "父菜单id", dataType = "Integer")
    private Integer parentId;


}
