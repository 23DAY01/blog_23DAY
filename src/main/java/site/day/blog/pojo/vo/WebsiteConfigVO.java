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

    @ApiModelProperty(name = "id", value = "配置id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "name", value = "网站名称", dataType = "String")
    private String name;

    @ApiModelProperty(name = "intro", value = "网站简介", dataType = "String")
    private String intro;

    @ApiModelProperty(name = "author", value = "网站作者", dataType = "String")
    private String author;

    @ApiModelProperty(name = "url", value = "网站地址", dataType = "String")
    private String url;

    @ApiModelProperty(name = "notice", value = "网站通知", dataType = "String")
    private String notice;

    @ApiModelProperty(name = "about", value = "关于我", dataType = "String")
    private String about;

    @ApiModelProperty(name = "github", value = "github", dataType = "String")
    private String github;

    @ApiModelProperty(name = "qq", value = "qq号", dataType = "String")
    private String qq;

    @ApiModelProperty(name = "beianId", value = "备案号", dataType = "String")
    private String beianId;

    @ApiModelProperty(name = "authorAvatar", value = "作者头像", dataType = "String")
    private String authorAvatar;

    @ApiModelProperty(name = "userAvatar", value = "用户头像", dataType = "String")
    private String userAvatar;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;


}
