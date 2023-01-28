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
 * @ClassName MessageVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "MessageVO", description = "留言表")
public class MessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    private String nickname;

    @ApiModelProperty(name = "avatar", value = "用户头像", dataType = "String")
    private String avatar;

    @ApiModelProperty(name = "messageContent", value = "留言内容", dataType = "String")
    private String messageContent;

    @ApiModelProperty(name = "ipAddress", value = "ip地址", dataType = "String")
    private String ipAddress;

    @ApiModelProperty(name = "ipSource", value = "ip来源", dataType = "String")
    private String ipSource;

    @ApiModelProperty(name = "speed", value = "弹幕速度", dataType = "Integer")
    private Integer speed;


}
