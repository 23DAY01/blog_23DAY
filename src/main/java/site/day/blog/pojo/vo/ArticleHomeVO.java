package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 首页文章
 * @ClassName ArticleHomeVO
 * @Author 23DAY
 * @Date 2023/1/26 11:22
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("首页文章")
public class ArticleHomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "作者id", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(name = "categoryId", value = "文章分类", dataType = "Integer")
    private Integer categoryId;

    @ApiModelProperty(name = "categoryName", value = "分类名", dataType = "String")
    private String categoryName;

    @ApiModelProperty(name = "tagDTOList", value = "文章标签列表", dataType = "List<TagDTO>")
    private List<TagVO> tagList;

    @ApiModelProperty(name = "articleCover", value = "文章缩略图", dataType = "String")
    private String articleCover;

    @ApiModelProperty(name = "articleTitle", value = "标题", dataType = "String")
    private String articleTitle;

    @ApiModelProperty(name = "articleContent", value = "内容", dataType = "String")
    private String articleContent;

    @ApiModelProperty(name = "type", value = "文章类型 1原创 2转载 3翻译", dataType = "Integer")
    private Integer type;

    @ApiModelProperty(name = "originalUrl", value = "原文链接", dataType = "String")
    private String originalUrl;

    @ApiModelProperty(name = "isTop", value = "是否置顶 0否 1是", dataType = "Boolean")
    private Boolean isTop;

    @ApiModelProperty(name = "status", value = "文章状态 1公开 2私密 3草稿", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;




}
