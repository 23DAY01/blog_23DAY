package site.day.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 网站配置表
 * @ClassName WebsiteConfig
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("day_website_config")
public class WebsiteConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 网站名称
     */
    @TableField("name")
    private String name;

    /**
     * 网站简介
     */
    @TableField("intro")
    private String intro;

    /**
     * 网站作者
     */
    @TableField("author")
    private String author;

    /**
     * 网站地址
     */
    @TableField("url")
    private String url;

    /**
     * 网站通知
     */
    @TableField("notice")
    private String notice;

    /**
     * github
     */
    @TableField("github")
    private String github;

    /**
     * qq号
     */
    @TableField("qq")
    private String qq;

    /**
     * 备案号
     */
    @TableField("beian_id")
    private String beianId;

    /**
     * 作者头像
     */
    @TableField("author_avatar")
    private String authorAvatar;

    /**
     * 用户头像
     */
    @TableField("user_avatar")
    private String userAvatar;

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
