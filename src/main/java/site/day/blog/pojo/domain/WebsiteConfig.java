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
 * 网站配置表
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("day_website_config")
@ApiModel(value = "WebsiteConfig对象", description = "网站配置表")
public class WebsiteConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("网站名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("网站简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty("网站作者")
    @TableField("author")
    private String author;

    @ApiModelProperty("网站地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("网站通知")
    @TableField("notice")
    private String notice;

    @ApiModelProperty("github")
    @TableField("github")
    private String github;

    @ApiModelProperty("qq号")
    @TableField("qq")
    private String qq;

    @ApiModelProperty("备案号")
    @TableField("beian_id")
    private String beianId;

    @ApiModelProperty("作者头像")
    @TableField("author_avatar")
    private String authorAvatar;

    @ApiModelProperty("用户头像")
    @TableField("user_avatar")
    private String userAvatar;

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
