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
 * 评论表
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("day_comment")
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("评论用户Id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("所属主体id")
    @TableField("affiliation_id")
    private Integer affiliationId;

    @ApiModelProperty("评论内容")
    @TableField("comment_content")
    private String commentContent;

    @ApiModelProperty("回复用户id")
    @TableField("reply_user_id")
    private Integer replyUserId;

    @ApiModelProperty("父评论id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("评论类型 1.文章 2.友链 3.说说")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("是否审核")
    @TableField("is_review")
    private Boolean isReview;

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
