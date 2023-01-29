package site.day.blog.pojo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class CommentHomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 评论用户Id
     */
    @ApiModelProperty(name = "userId", value = "评论用户Id", dataType = "Integer")
    private Integer userId;
    
    /**
     * 用户名
     */
    @ApiModelProperty(name = "nickName", value = "用户名", dataType = "String")
    private String nickName;
    
    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "头像", dataType = "String")
    private String avatar;
    
    /**
     * 用户网站
     */
    @ApiModelProperty(name = "website", value = "用户网站", dataType = "String")
    private String website;

    /**
     * 所属主体id
     */
    @ApiModelProperty(name = "affiliationId", value = "所属主体id", dataType = "Integer")
    private Integer affiliationId;

    /**
     * 评论内容
     */
    @ApiModelProperty(name = "commentContent", value = "评论内容", dataType = "String")
    private String commentContent;

    /**
     * 被回复用户id
     */
    @ApiModelProperty(name = "replyUserId", value = "被回复用户id", dataType = "Integer")
    private Integer replyUserId;
    
    /**
     * 被回复用户名
     */
    @ApiModelProperty(name = "replyNickname", value = "被回复用户名", dataType = "String")
    private String replyNickname;
    
    /**
     * 被回复用户网站
     */
    @ApiModelProperty(name = "replyWebsite", value = "被回复用户网站", dataType = "String")
    private String replyWebsite;

    /**
     * 父评论id
     */
    @ApiModelProperty(name = "parentId", value = "父评论id", dataType = "Integer")
    private Integer parentId;
    
    /**
     * 顶级评论id,默认为自身id
     */
    @ApiModelProperty(name = "topId", value = "顶级评论id,默认为自身id", dataType = "Integer")
    private Integer topId;

    /**
     * 评论类型 1.文章 2.友链 3.说说
     */
    @ApiModelProperty(name = "type", value = "评论类型 1.文章 2.友链 3.说说", dataType = "Integer")
    private Integer type;

    /**
     * 是否审核
     */
    @ApiModelProperty(name = "isReview", value = "是否审核", dataType = "Boolean")
    private Boolean isReview;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;
    
    /**
     * 点赞量
     */
    @ApiModelProperty(name = "likeCount", value = "点赞量", dataType = "Integer")
    private Integer likeCount;
    
    /**
     * 回复量
     */
    @ApiModelProperty(name = "replyCount", value = "回复量", dataType = "Integer")
    private Integer replyCount;
    
    /**
     * 当前评论的回复
     */
    @ApiModelProperty(name = "replyList", value = "当前评论的回复", dataType = "List<CommentHomeVO>")
    private List<CommentHomeVO> replyList;


}
