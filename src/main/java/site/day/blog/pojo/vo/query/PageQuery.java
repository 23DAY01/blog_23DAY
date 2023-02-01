package site.day.blog.pojo.vo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

import static site.day.blog.constant.CommonConst.PAGE_DEFAULT_CURRENT;
import static site.day.blog.constant.CommonConst.PAGE_DEFAULT_SIZE;


/**
 * @Description 分页
 * @ClassName PageQuery
 * @Author 23DAY
 * @Date 2023/1/25 19:26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PageQuery", description = "分页")
public class PageQuery {

    /**
     * 页码，默认为1
     */
    @ApiModelProperty(name = "current", value = "页码，默认为1", dataType = "Long")
    @Min(0)
    private Long current = PAGE_DEFAULT_CURRENT;

    /**
     * 条数，默认为10
     */
    @ApiModelProperty(name = "size", value = "条数，默认为10", dataType = "Long")
    @Min(0)
    private Long size;

}
