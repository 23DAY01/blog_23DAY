package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 添加页面
 * @ClassName PageSaveQuery
 * @Author 23DAY
 * @Date 2023/2/1 15:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PageSaveQuery", description = "添加页面")
public class PageSaveQuery {

    /**
     * 页面id
     */
    @ApiModelProperty(name = "id", value = "页面id", dataType = "Integer")
    private Integer id;

    /**
     * 页面名称
     */
    @ApiModelProperty(name = "pageName", value = "页面名称", dataType = "String")
    private String pageName;

    /**
     * 页面标签
     */
    @ApiModelProperty(name = "pageLabel", value = "页面标签", dataType = "String")
    private String pageLabel;

    /**
     * 页面封面
     */
    @ApiModelProperty(name = "pageCover", value = "页面封面", dataType = "String")
    private String pageCover;


}
