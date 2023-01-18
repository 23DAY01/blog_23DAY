package site.day.blog.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 操作日志表
 * @ClassName OperationLog
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("day_operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 操作模块
     */
    @TableField("opt_module")
    private String optModule;

    /**
     * 操作类型
     */
    @TableField("opt_type")
    private String optType;

    /**
     * 操作url
     */
    @TableField("opt_url")
    private String optUrl;

    /**
     * 操作方法
     */
    @TableField("opt_method")
    private String optMethod;

    /**
     * 操作描述
     */
    @TableField("opt_desc")
    private String optDesc;

    /**
     * 请求参数
     */
    @TableField("request_param")
    private String requestParam;

    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 响应数据
     */
    @TableField("response_data")
    private String responseData;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 用户昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 操作ip
     */
    @TableField("ip_address")
    private String ipAddress;

    /**
     * 操作地址
     */
    @TableField("ip_source")
    private String ipSource;

    /**
     * 逻辑删除 0否 NULL是
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
