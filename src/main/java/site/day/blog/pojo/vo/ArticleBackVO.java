package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import site.day.blog.pojo.dto.TagDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 后台文章
 * @ClassName ArticleBackVO
 * @Author 23DAY
 * @Date 2023/1/29 12:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "ArticleBackVO", description = "文章表")
public class ArticleBackVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 作者id
     */
    @ApiModelProperty(name = "userId", value = "作者id", dataType = "Integer")
    private Integer userId;

    /**
     * 文章分类
     */
    @ApiModelProperty(name = "categoryId", value = "文章分类", dataType = "Integer")
    private Integer categoryId;

    /**
     * 文章分类名
     */
    @ApiModelProperty(name = "categoryName", value = "文章分类名", dataType = "String")
    private String categoryName;

    /**
     * 文章标签列表
     */
    @ApiModelProperty(name = "tagDTOList", value = "文章标签列表", dataType = "List<TagDTO>")
    private List<TagDTO> tagList;

    /**
     * 文章缩略图
     */
    @ApiModelProperty(name = "articleCover", value = "文章缩略图", dataType = "String")
    private String articleCover;

    /**
     * 标题
     */
    @ApiModelProperty(name = "articleTitle", value = "标题", dataType = "String")
    private String articleTitle;

    /**
     * 内容
     */
    @ApiModelProperty(name = "articleContent", value = "内容", dataType = "String")
    private String articleContent;

    /**
     * 文章类型 1原创 2转载 3翻译
     */
    @ApiModelProperty(name = "type", value = "文章类型 1原创 2转载 3翻译", dataType = "Integer")
    private Integer type;

    /**
     * 原文链接
     */
    @ApiModelProperty(name = "originalUrl", value = "原文链接", dataType = "String")
    private String originalUrl;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    /**
     * 文章浏览量
     */
    @ApiModelProperty(name = "viewCount", value = "文章浏览量", dataType = "Integer")
    private Integer viewCount;

    /**
     * 点赞量
     */
    @ApiModelProperty(name = "likeCount", value = "点赞量", dataType = "Integer")
    private Integer likeCount;

    /**
     * 是否置顶 0否 1是
     */
    @ApiModelProperty(name = "isTop", value = "是否置顶 0否 1是", dataType = "Boolean")
    private Boolean isTop;

    /**
     * 文章状态 1公开 2私密 3草稿
     */
    @ApiModelProperty(name = "status", value = "文章状态 1公开 2私密 3草稿", dataType = "Integer")
    private Integer status;

    /**
     * 删除
     */
    @ApiModelProperty(name = "deleted", value = "删除", dataType = "Boolean")
    private Boolean deleted;


}
