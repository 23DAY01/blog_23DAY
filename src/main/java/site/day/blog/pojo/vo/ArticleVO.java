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
 * @ClassName ArticleVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "ArticleVO", description = "文章表")
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "作者id", dataType = "Integer")
    private Integer userId;

    @NotNull(message = "categoryId不能为空")
    @ApiModelProperty(name = "categoryId", value = "文章分类", dataType = "Integer")
    private Integer categoryId;

    @NotNull(message = "articleThumbnail不能为空")
    @ApiModelProperty(name = "articleThumbnail", value = "文章缩略图", dataType = "String")
    private String articleThumbnail;

    @ApiModelProperty(name = "articleTitle", value = "标题", dataType = "String")
    private String articleTitle;

    @ApiModelProperty(name = "articleContent", value = "内容", dataType = "String")
    private String articleContent;

    @ApiModelProperty(name = "type", value = "文章类型 1原创 2转载 3翻译", dataType = "Integer")
    private Integer type;

    @NotNull(message = "originalUrl不能为空")
    @ApiModelProperty(name = "originalUrl", value = "原文链接", dataType = "String")
    private String originalUrl;

    @ApiModelProperty(name = "isTop", value = "是否置顶 0否 1是", dataType = "Boolean")
    private Boolean isTop;

    @ApiModelProperty(name = "status", value = "文章状态 1公开 2私密 3草稿", dataType = "Integer")
    private Integer status;

    @NotNull(message = "deleted不能为空")
    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @NotNull(message = "updateTime不能为空")
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
