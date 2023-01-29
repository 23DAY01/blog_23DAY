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
     * 父菜单id
     */
    @ApiModelProperty(name = "parentId", value = "父菜单id", dataType = "Integer")
    private Integer parentId;

    /**
     * 是否隐藏  0否1是
     */
    @ApiModelProperty(name = "isHidden", value = "是否隐藏  0否1是", dataType = "Boolean")
    private Boolean isHidden;

    /**
     * 逻辑删除 0否 NULL是
     */
    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
