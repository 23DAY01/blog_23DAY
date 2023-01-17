package site.day.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("day_article")
@ApiModel(value = "Article对象", description = "文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("作者id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("文章分类")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("文章缩略图")
    @TableField("article_thumbnail")
    private String articleThumbnail;

    @ApiModelProperty("标题")
    @TableField("article_title")
    private String articleTitle;

    @ApiModelProperty("内容")
    @TableField("article_content")
    private String articleContent;

    @ApiModelProperty("文章类型 1原创 2转载 3翻译")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("原文链接")
    @TableField("original_url")
    private String originalUrl;

    @ApiModelProperty("是否置顶 0否 1是")
    @TableField("is_top")
    private Boolean isTop;

    @ApiModelProperty("文章状态 1公开 2私密 3草稿")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("逻辑删除 0否 NULL是")
    @TableField("delete")
    private Boolean delete;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
