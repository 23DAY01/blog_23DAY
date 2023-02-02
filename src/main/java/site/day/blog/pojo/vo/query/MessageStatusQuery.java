package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description 审核留言
 * @ClassName MessageStatusQuery
 * @Author 23DAY
 * @Date 2023/2/1 11:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MessageStatusQuery", description = "审核留言")
public class MessageStatusQuery {


    /**
     * 留言id
     */
    @ApiModelProperty(name = "idList",value = "留言id",dataType = "List<Integer>")
    @NotEmpty
    private List<Integer> idList;

    /**
     * 审核状态
     */
    @ApiModelProperty(name = "isReview",value = "审核状态",dataType = "Boolean")
    @NotNull
    private Boolean isReview;

}
