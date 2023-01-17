package site.day.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("day_operation_log")
@ApiModel(value = "OperationLog对象", description = "操作日志表")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("操作模块")
    @TableField("opt_module")
    private String optModule;

    @ApiModelProperty("操作类型")
    @TableField("opt_type")
    private String optType;

    @ApiModelProperty("操作url")
    @TableField("opt_url")
    private String optUrl;

    @ApiModelProperty("操作方法")
    @TableField("opt_method")
    private String optMethod;

    @ApiModelProperty("操作描述")
    @TableField("opt_desc")
    private String optDesc;

    @ApiModelProperty("请求参数")
    @TableField("request_param")
    private String requestParam;

    @ApiModelProperty("请求方式")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty("响应数据")
    @TableField("response_data")
    private String responseData;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("操作ip")
    @TableField("ip_address")
    private String ipAddress;

    @ApiModelProperty("操作地址")
    @TableField("ip_source")
    private String ipSource;

    @ApiModelProperty("逻辑删除 0否 NULL是")
    @TableField("delete")
    private Boolean delete;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
