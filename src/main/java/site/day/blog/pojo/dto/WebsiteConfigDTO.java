package site.day.blog.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName WebsiteConfigDTO
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WebsiteConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Integer id;

    /**
     * 网站名称
     */
    private String name;

    /**
     * 网站简介
     */
    private String intro;

    /**
     * 网站作者
     */
    private String author;

    /**
     * 网站地址
     */
    private String url;

    /**
     * 网站通知
     */
    private String notice;

    /**
     * 关于我
     */
    private String about;

    /**
     * github
     */
    private String github;

    /**
     * qq号
     */
    private String qq;

    /**
     * 备案号
     */
    private String beianId;

    /**
     * 作者头像
     */
    private String authorAvatar;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;




}
