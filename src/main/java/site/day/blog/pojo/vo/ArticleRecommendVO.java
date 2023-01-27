package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description
 * @ClassName ArticleRecommendVO
 * @Author 23DAY
 * @Date 2023/1/27 15:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章推荐")
public class ArticleRecommendVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "文章id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "articleTitle", value = "文章标题", dataType = "String")
    private String articleTitle;

    @ApiModelProperty(name = "articleCover", value = "文章封面", dataType = "String")
    private String articleCover;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;
}
