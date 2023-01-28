package site.day.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 评论表
 * @ClassName Comment
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@TableName("day_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 评论用户Id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 所属主体id
     */
    @TableField("affiliation_id")
    private Integer affiliationId;

    /**
     * 评论类型 1.文章 2.友链 3.说说
     */
    @TableField("type")
    private Integer type;

    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;

    /**
     * 回复用户id
     */
    @TableField("reply_user_id")
    private Integer replyUserId;

    /**
     * 父评论id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 顶级评论id，默认为自身id
     */
    @TableField("top_id")
    private Integer topId;

    /**
     * 是否审核
     */
    @TableField("is_review")
    private Boolean isReview;

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
