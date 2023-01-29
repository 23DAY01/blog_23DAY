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
 * @ClassName OperationLogVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "OperationLogVO", description = "操作日志表")
public class OperationLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", value = "主键id", dataType = "Integer")
    private Integer id;

    /**
     * 操作模块
     */
    @ApiModelProperty(name = "optModule", value = "操作模块", dataType = "String")
    private String optModule;

    /**
     * 操作类型
     */
    @ApiModelProperty(name = "optType", value = "操作类型", dataType = "String")
    private String optType;

    /**
     * 操作url
     */
    @ApiModelProperty(name = "optUrl", value = "操作url", dataType = "String")
    private String optUrl;

    /**
     * 操作方法
     */
    @ApiModelProperty(name = "optMethod", value = "操作方法", dataType = "String")
    private String optMethod;

    /**
     * 操作描述
     */
    @ApiModelProperty(name = "optDesc", value = "操作描述", dataType = "String")
    private String optDesc;

    /**
     * 请求参数
     */
    @ApiModelProperty(name = "requestParam", value = "请求参数", dataType = "String")
    private String requestParam;

    /**
     * 请求方式
     */
    @ApiModelProperty(name = "requestMethod", value = "请求方式", dataType = "String")
    private String requestMethod;

    /**
     * 响应数据
     */
    @ApiModelProperty(name = "responseData", value = "响应数据", dataType = "String")
    private String responseData;

    /**
     * 用户id
     */
    @ApiModelProperty(name = "userId", value = "用户id", dataType = "Integer")
    private Integer userId;

    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    private String nickname;

    /**
     * 操作ip
     */
    @ApiModelProperty(name = "ipAddress", value = "操作ip", dataType = "String")
    private String ipAddress;

    /**
     * 操作地址
     */
    @ApiModelProperty(name = "ipSource", value = "操作地址", dataType = "String")
    private String ipSource;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;


}
