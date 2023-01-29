package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 文章排行
 * @ClassName ArticleRankVO
 * @Author 23DAY
 * @Date 2023/1/27 11:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章排行")
public class ArticleRankVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @ApiModelProperty(name = "id",value = "文章id",dataType = "Integer")
    private Integer id;

    /**
     * 文章标题
     */
    @ApiModelProperty(name = "articleTitle",value = "文章标题",dataType = "String")
    private String articleTitle;

    /**
     * 文章浏览量
     */
    @ApiModelProperty(name = "viewCount",value = "文章浏览量",dataType = "Integer")
    private Integer viewCount;

}
