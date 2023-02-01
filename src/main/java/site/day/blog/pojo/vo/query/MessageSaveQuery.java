package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 添加留言
 * @ClassName MessageQuery
 * @Author 23DAY
 * @Date 2023/1/28 19:10
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MenuSaveQuery", description = "添加留言")
public class MessageSaveQuery {

    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "String")
    private String avatar;

    /**
     * 留言内容
     */
    @ApiModelProperty(name = "messageContent", value = "留言内容", dataType = "String")
    @NotBlank(message = "留言不能为空")
    private String messageContent;

    /**
     * 弹幕速度
     */
    @ApiModelProperty(name = "speed", value = "弹幕速度", dataType = "Integer")
    private Integer speed;


}
