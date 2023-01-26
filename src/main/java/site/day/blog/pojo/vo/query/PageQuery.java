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
 * @Description
 * @ClassName PageQuery
 * @Author 23DAY
 * @Date 2023/1/25 19:26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页")
public class PageQuery {

    @ApiModelProperty(name = "current", value = "页码，默认为1", dataType = "Long")
    @Min(0)
    private Long current = PAGE_DEFAULT_CURRENT;

    @ApiModelProperty(name = "size", value = "条数，默认为10", dataType = "Long")
    @Min(0)
    private Long size = PAGE_DEFAULT_SIZE;

}
