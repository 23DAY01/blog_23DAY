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
 * @ClassName WebsiteConfigVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "WebsiteConfigVO", description = "网站配置表")
public class WebsiteConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置id
     */
    @ApiModelProperty(name = "id", value = "配置id", dataType = "Integer")
    private Integer id;

    /**
     * 网站名称
     */
    @ApiModelProperty(name = "name", value = "网站名称", dataType = "String")
    private String name;

    /**
     * 网站简介
     */
    @ApiModelProperty(name = "intro", value = "网站简介", dataType = "String")
    private String intro;

    /**
     * 网站作者
     */
    @ApiModelProperty(name = "author", value = "网站作者", dataType = "String")
    private String author;

    /**
     * 网站地址
     */
    @ApiModelProperty(name = "url", value = "网站地址", dataType = "String")
    private String url;

    /**
     * 网站通知
     */
    @ApiModelProperty(name = "notice", value = "网站通知", dataType = "String")
    private String notice;

    /**
     * 关于我
     */
    @ApiModelProperty(name = "about", value = "关于我", dataType = "String")
    private String about;

    /**
     * github
     */
    @ApiModelProperty(name = "github", value = "github", dataType = "String")
    private String github;

    /**
     * qq号
     */
    @ApiModelProperty(name = "qq", value = "qq号", dataType = "String")
    private String qq;

    /**
     * 备案号
     */
    @ApiModelProperty(name = "beianId", value = "备案号", dataType = "String")
    private String beianId;

    /**
     * 作者头像
     */
    @ApiModelProperty(name = "authorAvatar", value = "作者头像", dataType = "String")
    private String authorAvatar;

    /**
     * 用户头像
     */
    @ApiModelProperty(name = "userAvatar", value = "用户头像", dataType = "String")
    private String userAvatar;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;


}
