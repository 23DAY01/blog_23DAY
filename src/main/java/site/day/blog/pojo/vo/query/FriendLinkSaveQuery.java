package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 添加友链
 * @ClassName FriendLinkSaveQuery
 * @Author 23DAY
 * @Date 2023/1/29 21:28
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FriendLinkSaveQuery", description = "添加友链")
public class FriendLinkSaveQuery {

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "友链id", dataType = "Integer")
    private Integer id;

    /**
     * 链接名
     */
    @NotBlank(message = "链接名不能为空")
    @ApiModelProperty(name = "linkName", value = "友链名", dataType = "String", required = true)
    private String linkName;

    /**
     * 链接头像
     */
    @NotBlank(message = "链接头像不能为空")
    @ApiModelProperty(name = "linkAvatar", value = "友链头像", dataType = "String", required = true)
    private String linkAvatar;

    /**
     * 链接地址
     */
    @NotBlank(message = "链接地址不能为空")
    @ApiModelProperty(name = "linkAddress", value = "友链头像", dataType = "String", required = true)
    private String linkAddress;

    /**
     * 介绍
     */
    @NotBlank(message = "链接介绍不能为空")
    @ApiModelProperty(name = "linkInfo", value = "友链头像", dataType = "String", required = true)
    private String linkInfo;

}
