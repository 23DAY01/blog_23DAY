package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 文章状态修改
 * @ClassName ArticleStatusQuery
 * @Author 23DAY
 * @Date 2023/1/29 18:08
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ArticleStatusQuery", description = "文章状态修改")
public class ArticleStatusQuery {

    /**
     * 文章id
     */
    @ApiModelProperty(name = "id", value = "文章id", dataType = "Integer")
    @NotBlank(message = "文章id不能为空")
    private Integer id;

    /**
     * 文章状态
     */
    @ApiModelProperty(name = "status", value = "文章状态", dataType = "Integer")
    private Integer status;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型", dataType = "Integer")
    private Integer type;

    /**
     * 是否置顶
     */
    @ApiModelProperty(name = "isTop", value = "是否置顶", dataType = "Integer")
    private Boolean isTop;


}
