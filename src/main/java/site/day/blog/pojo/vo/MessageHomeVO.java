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
 * @ClassName MessageHomeVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "MessageHomeVO", description = "留言表")
public class MessageHomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", value = "主键id", dataType = "Integer")
    private Integer id;

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
    private String messageContent;

    /**
     * ip地址
     */
    @ApiModelProperty(name = "ipAddress", value = "ip地址", dataType = "String")
    private String ipAddress;

    /**
     * ip来源
     */
    @ApiModelProperty(name = "ipSource", value = "ip来源", dataType = "String")
    private String ipSource;

    /**
     * 弹幕速度
     */
    @ApiModelProperty(name = "speed", value = "弹幕速度", dataType = "Integer")
    private Integer speed;


}
