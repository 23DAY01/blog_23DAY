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
 * @ClassName TalkHomeVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "TalkHomeVO", description = "说说表")
public class TalkHomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "nickName", value = "用户名", dataType = "String")
    private String nickName;

    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "头像", dataType = "String")
    private String avatar;

    @ApiModelProperty(name = "content", value = "说说内容", dataType = "String")
    private String content;

    @ApiModelProperty(name = "image", value = "图片", dataType = "String")
    private String image;

    @ApiModelProperty(name = "isTop", value = "是否置顶 0否 1是", dataType = "Boolean")
    private Boolean isTop;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;


}
