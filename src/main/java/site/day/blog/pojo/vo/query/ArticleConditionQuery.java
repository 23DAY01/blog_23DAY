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
@ApiModel("页面")
public class ArticleConditionQuery {

    @ApiModelProperty(name = "current", value = "页码，默认为1", dataType = "Long")
    @Min(0)
    private Long current = PAGE_DEFAULT_CURRENT;

    @ApiModelProperty(name = "size", value = "条数，默认为10", dataType = "Long")
    @Min(0)
    private Long size = PAGE_DEFAULT_SIZE;

    @ApiModelProperty(name = "categoryId", value = "分类id", dataType = "Integer")
    private Integer categoryId;

    @ApiModelProperty(name = "tagId", value = "标签Id", dataType = "Integer")
    private Integer tagId;

    @ApiModelProperty(name = "keyWord", value = "关键字", dataType = "String")
    private String keyWord;

    @ApiModelProperty(name = "status", value = "状态", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(name = "type", value = "文章类型", dataType = "Integer")
    private Integer type;

    @ApiModelProperty(name = "startTime", value = "起始时间", dataType = "LocalDateTime")
    private LocalDateTime startTime;

    @ApiModelProperty(name = "endTime", value = "结束时间", dataType = "LocalDateTime")
    private LocalDateTime endTime;

    @ApiModelProperty(name = "deleted", value = "是否删除", dataType = "Boolean")
    private Boolean deleted;


}
