package site.day.blog.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName TalkDTO
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TalkDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
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
     * 说说内容
     */
    private String content;

    /**
     * 图片
     */
    private String image;

    /**
     * 是否置顶 0否 1是
     */
    private Boolean isTop;

    /**
     * 状态 0公开 1私密
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 点赞量
     */
    private Integer likeCount;


}
