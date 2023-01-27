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

    @ApiModelProperty(name = "id", value = "主键id", dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "optModule", value = "操作模块", dataType = "String")
    private String optModule;

    @ApiModelProperty(name = "optType", value = "操作类型", dataType = "String")
    private String optType;

    @ApiModelProperty(name = "optUrl", value = "操作url", dataType = "String")
    private String optUrl;

    @ApiModelProperty(name = "optMethod", value = "操作方法", dataType = "String")
    private String optMethod;

    @ApiModelProperty(name = "optDesc", value = "操作描述", dataType = "String")
    private String optDesc;

    @ApiModelProperty(name = "requestParam", value = "请求参数", dataType = "String")
    private String requestParam;

    @ApiModelProperty(name = "requestMethod", value = "请求方式", dataType = "String")
    private String requestMethod;

    @ApiModelProperty(name = "responseData", value = "响应数据", dataType = "String")
    private String responseData;

    @ApiModelProperty(name = "userId", value = "用户id", dataType = "Integer")
    private Integer userId;

    @ApiModelProperty(name = "nickname", value = "用户昵称", dataType = "String")
    private String nickname;

    @ApiModelProperty(name = "ipAddress", value = "操作ip", dataType = "String")
    private String ipAddress;

    @ApiModelProperty(name = "ipSource", value = "操作地址", dataType = "String")
    private String ipSource;

    @ApiModelProperty(name = "deleted", value = "逻辑删除 0否 NULL是", dataType = "Boolean")
    private Boolean deleted;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime", value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updateTime;


}
