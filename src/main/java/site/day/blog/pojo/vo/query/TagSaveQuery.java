package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 添加标签
 * @ClassName TagSaveQuery
 * @Author 23DAY
 * @Date 2023/2/1 20:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TagSaveQuery", description = "添加标签")
public class TagSaveQuery {

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer")
    private Integer id;

    /**
     * 标签名称
     */
    @ApiModelProperty(name = "tagName", value = "标签名称", dataType = "String")
    private String tagName;

}
