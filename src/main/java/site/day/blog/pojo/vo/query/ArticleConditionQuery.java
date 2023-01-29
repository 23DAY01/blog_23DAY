package site.day.blog.pojo.vo.query;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

import static site.day.blog.constant.CommonConst.*;

/**
 * @Description
 * @ClassName PageVO
 * @Author 23DAY
 * @Date 2023/1/23 22:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章状态查询")
public class ArticleConditionQuery {

    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", dataType = "Integer")
    private Integer categoryId;

    /**
     * 标签Id
     */
    @ApiModelProperty(name = "tagId", value = "标签Id", dataType = "Integer")
    private Integer tagId;

    /**
     * 关键字
     */
    @ApiModelProperty(name = "keyWord", value = "关键字", dataType = "String")
    private String keyWord;

    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "状态", dataType = "Integer")
    private Integer status;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型", dataType = "Integer")
    private Integer type;

    /**
     * 起始时间
     */
    @ApiModelProperty(name = "startTime", value = "起始时间", dataType = "LocalDateTime")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(name = "endTime", value = "结束时间", dataType = "LocalDateTime")
    private LocalDateTime endTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(name = "deleted", value = "是否删除", dataType = "Boolean")
    private Boolean deleted;


}
