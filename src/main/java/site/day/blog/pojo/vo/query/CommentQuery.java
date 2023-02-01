package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 评论请求
 * @ClassName CommentQuery
 * @Author 23DAY
 * @Date 2023/1/28 11:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CommentQuery", description = "评论请求")
public class CommentQuery {

    /**
     * 属主id
     */
    @ApiModelProperty(name = "affiliationId",value = "属主id",dataType = "Integer")
    private Integer affiliationId;

    /**
     * 属主类型
     */
    @ApiModelProperty(name = "type",value = "属主类型",dataType = "Integer")
    private Integer type;

    /**
     * 父评论id
     */
    @ApiModelProperty(name = "parentId",value = "父评论id",dataType = "Integer")
    private Integer parentId;

    /**
     * 顶级评论id，默认为自身id
     */
    @ApiModelProperty(name = "topId",value = "顶级评论id，默认为自身id",dataType = "Integer")
    private Integer topId;

    /**
     * 被回复用户id
     */
    @ApiModelProperty(name = "replyUserId",value = "被回复用户id",dataType = "Integer")
    private Integer replyUserId;

    /**
     * 评论内容
     */
    @ApiModelProperty(name = "commentContent",value = "评论内容",dataType = "String")
    private String commentContent;

}
