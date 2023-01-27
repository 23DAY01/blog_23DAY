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
 * @ClassName TalkVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "TalkVO", description = "说说表")
public class TalkVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "userId", value = "用户id", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(name = "content", value = "说说内容", dataType = "String")
    private String content;

    @ApiModelProperty(name = "image", value = "图片", dataType = "String")
    private String image;

    @ApiModelProperty(name = "isTop", value = "是否置顶 0否 1是", dataType = "Boolean")
    private Boolean isTop;

    @ApiModelProperty(name = "status", value = "状态 0公开 1私密", dataType = "Boolean")
    private Boolean status;

    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
