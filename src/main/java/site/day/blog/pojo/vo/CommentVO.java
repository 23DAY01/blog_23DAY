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
 * @ClassName CommentVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "CommentVO", description = "评论表")
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "评论用户Id", dataType = "Integer")
    private Integer userId;

    @NotNull(message = "affiliationId不能为空")
    @ApiModelProperty(name = "affiliationId", value = "所属主体id", dataType = "Integer")
    private Integer affiliationId;

    @ApiModelProperty(name = "commentContent", value = "评论内容", dataType = "String")
    private String commentContent;

    @NotNull(message = "replyUserId不能为空")
    @ApiModelProperty(name = "replyUserId", value = "回复用户id", dataType = "Integer")
    private Integer replyUserId;

    @NotNull(message = "parentId不能为空")
    @ApiModelProperty(name = "parentId", value = "父评论id", dataType = "Integer")
    private Integer parentId;

    @ApiModelProperty(name = "type", value = "评论类型 1.文章 2.友链 3.说说", dataType = "Integer")
    private Integer type;

    @ApiModelProperty(name = "isReview", value = "是否审核", dataType = "Boolean")
    private Boolean isReview;

    @NotNull(message = "deleted不能为空")
    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @NotNull(message = "updateTime不能为空")
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
