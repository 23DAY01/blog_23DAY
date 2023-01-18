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
 * @ClassName ResourceVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "ResourceVO", description = "资源表")
public class ResourceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "resourceName", value = "资源名称", dataType = "String")
    private String resourceName;

    @NotNull(message = "url不能为空")
    @ApiModelProperty(name = "url", value = "权限路径", dataType = "String")
    private String url;

    @NotNull(message = "requestMethod不能为空")
    @ApiModelProperty(name = "requestMethod", value = "请求方式", dataType = "String")
    private String requestMethod;

    @NotNull(message = "parentId不能为空")
    @ApiModelProperty(name = "parentId", value = "父权限id", dataType = "Integer")
    private Integer parentId;

    @ApiModelProperty(name = "isAnonymous", value = "是否可以匿名访问 0否 1是", dataType = "Boolean")
    private Boolean isAnonymous;

    @NotNull(message = "deleted不能为空")
    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @NotNull(message = "updateTime不能为空")
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
