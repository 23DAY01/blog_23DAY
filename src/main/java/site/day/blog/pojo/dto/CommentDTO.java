package site.day.blog.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName CommentDTO
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

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
     * 所属主体id
     */
    private Integer affiliationId;

    /**
     * 所属主题类型
     */
    private Integer type;

    /**
     * 顶级评论id，默认为自身id
     */
    private Integer topId;

    /**
     * 父评论id
     */
    private Integer parentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 点赞量
     */
    private Integer likeCount;

    /**
     * 回复量
     */
    private Integer replyCount;

    /**
     * 当前评论的回复
     */
    private List<CommentDTO> replyList;

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
     * 被回复用户头像
     */
    private String replyAvatar;

    /**
     * 是否审核
     */
    private Boolean isReview;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
