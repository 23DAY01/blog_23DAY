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

    @NotNull(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(name = "name", value = "网站名称", dataType = "String")
    private String name;

    @NotNull(message = "intro不能为空")
    @ApiModelProperty(name = "intro", value = "网站简介", dataType = "String")
    private String intro;

    @NotNull(message = "author不能为空")
    @ApiModelProperty(name = "author", value = "网站作者", dataType = "String")
    private String author;

    @NotNull(message = "url不能为空")
    @ApiModelProperty(name = "url", value = "网站地址", dataType = "String")
    private String url;

    @NotNull(message = "notice不能为空")
    @ApiModelProperty(name = "notice", value = "网站通知", dataType = "String")
    private String notice;

    @NotNull(message = "github不能为空")
    @ApiModelProperty(name = "github", value = "github", dataType = "String")
    private String github;

    @NotNull(message = "qq不能为空")
    @ApiModelProperty(name = "qq", value = "qq号", dataType = "String")
    private String qq;

    @NotNull(message = "beianId不能为空")
    @ApiModelProperty(name = "beianId", value = "备案号", dataType = "String")
    private String beianId;

    @NotNull(message = "authorAvatar不能为空")
    @ApiModelProperty(name = "authorAvatar", value = "作者头像", dataType = "String")
    private String authorAvatar;

    @NotNull(message = "userAvatar不能为空")
    @ApiModelProperty(name = "userAvatar", value = "用户头像", dataType = "String")
    private String userAvatar;

    @NotNull(message = "deleted不能为空")
    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @NotNull(message = "updateTime不能为空")
    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
