package site.day.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 文章表
 * @ClassName Article
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("day_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 作者id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 文章分类
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 文章封面
     */
    @TableField("article_cover")
    private String articleCover;

    /**
     * 标题
     */
    @TableField("article_title")
    private String articleTitle;

    /**
     * 内容
     */
    @TableField("article_content")
    private String articleContent;

    /**
     * 文章类型 1原创 2转载 3翻译
     */
    @TableField("type")
    private Integer type;

    /**
     * 原文链接
     */
    @TableField("original_url")
    private String originalUrl;

    /**
     * 是否置顶 0否 1是
     */
    @TableField("is_top")
    private Boolean isTop;

    /**
     * 文章状态 1公开 2私密 3草稿
     */
    @TableField("status")
    private Integer status;

    /**
     * 逻辑删除 0否 NULL是
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
