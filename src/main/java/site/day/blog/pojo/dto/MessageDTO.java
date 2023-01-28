package site.day.blog.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName MessageDTO
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 留言内容
     */
    private String messageContent;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

    /**
     * 弹幕速度
     */
    private Integer speed;

    /**
     * 是否审核
     */
    private Boolean isReview;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;



}
