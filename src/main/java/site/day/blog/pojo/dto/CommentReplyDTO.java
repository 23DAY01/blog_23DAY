package site.day.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description
 * @ClassName CommentReplyDTO
 * @Author 23DAY
 * @Date 2023/1/28 13:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommentReplyDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 父评论id
     */
    private Integer parentId;

    /**
     * 评论用户Id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户网站
     */
    private String website;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 回复量
     */
    private Integer replyCount;

    /**
     * 被回复用户id
     */
    private Integer replyUserId;

    /**
     * 被回复用户名
     */
    private String replyNickname;

    /**
     * 被回复用户网站
     */
    private String replyWebsite;

    /**
     * 是否审核
     */
    private Boolean isReview;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 点赞量
     */
    private Integer likeCount;

}
