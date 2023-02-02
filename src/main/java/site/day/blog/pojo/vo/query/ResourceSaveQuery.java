package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 添加资源
 * @ClassName ResourceSaveQuery
 * @Author 23DAY
 * @Date 2023/2/1 21:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ResourceSaveQuery", description = "添加资源")
public class ResourceSaveQuery {

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 资源名称
     */
    @ApiModelProperty(name = "resourceName", value = "资源名称", dataType = "String")
    private String resourceName;

    /**
     * 权限路径
     */
    @ApiModelProperty(name = "url", value = "权限路径", dataType = "String")
    private String url;

    /**
     * 请求方式
     */
    @ApiModelProperty(name = "requestMethod", value = "请求方式", dataType = "String")
    private String requestMethod;

    /**
     * 父权限id
     */
    @ApiModelProperty(name = "parentId", value = "父权限id", dataType = "Integer")
    private Integer parentId;

    /**
     * 是否可以匿名访问 0否 1是
     */
    @ApiModelProperty(name = "isAnonymous", value = "是否可以匿名访问 0否 1是", dataType = "Boolean")
    private Boolean isAnonymous;

}
