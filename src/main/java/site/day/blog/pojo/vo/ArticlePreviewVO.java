package site.day.blog.pojo.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.day.blog.pojo.dto.TagDTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 文章预览
 * @ClassName ArticlePreviewVO
 * @Author 23DAY
 * @Date 2023/1/22 21:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章预览")
public class ArticlePreviewVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "文章id", dataType = "Integer")
    private Integer id;

    /**
     * 文章封面
     */
    @ApiModelProperty(name = "articleCover", value = "文章封面", dataType = "String")
    private String articleCover;

    /**
     * 标题
     */
    @ApiModelProperty(name = "articleTitle", value = "标题", dataType = "String")
    private String articleTitle;

    /**
     * 分类Id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", dataType = "Integer")
    private Integer categoryId;

    /**
     * 文章分类名
     */
    @ApiModelProperty(name = "categoryName", value = "分类名", dataType = "String")
    private String categoryName;

    /**
     * 文章标签
     */
    @ApiModelProperty(name = "tagDTOList", value = "文章标签列表", dataType = "List<TagDTO>")
    private List<TagVO> tagList;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;


}
