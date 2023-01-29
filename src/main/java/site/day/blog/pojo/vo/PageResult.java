package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.day.blog.utils.PageUtil;

import java.util.Collections;
import java.util.List;

/**
 * @Description 分页结果封装
 * @ClassName PageResult
 * @Author 23DAY
 * @Date 2023/1/25 21:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "PageResult", description = "分页结果")
public class PageResult<T> {

    /**
     * 分页结果
     */
    @ApiModelProperty(name = "record", value = "分页结果", dataType = "List<T>")
    private List<T> records;

    /**
     * 查询总数
     */
    @ApiModelProperty(name = "total", value = "查询总数", dataType = "long")
    private long total;

    /**
     * 查询条数
     */
    @ApiModelProperty(name = "size", value = "查询条数", dataType = "size")
    private long size;

    /**
     * 查询页码
     */
    @ApiModelProperty(name = "current", value = "查询页码", dataType = "current")
    private long current;

    private static <T> PageResult<Object> doBuild(List<T> list) {
        PageResult<Object> pageResult = new PageResult<>();
        pageResult.setCurrent(PageUtil.getCurrent());
        pageResult.setSize(PageUtil.getSize());
        pageResult.setTotal(PageUtil.getTotal());
        pageResult.setRecords(Collections.singletonList(list));
        return pageResult;
    }
    public static <T> PageResult<Object> build(List<T> list){
        return doBuild(list);
    }


}
