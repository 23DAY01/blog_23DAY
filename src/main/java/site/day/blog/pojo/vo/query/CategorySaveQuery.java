package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 分类新增或修改
 * @ClassName CategorySaveQuery
 * @Author 23DAY
 * @Date 2023/1/29 19:48
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CategorySaveQuery", description = "分类新增或修改")
public class CategorySaveQuery {

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    @ApiModelProperty(name = "categoryName", value = "分类名", dataType = "String")
    private String categoryName;

}
