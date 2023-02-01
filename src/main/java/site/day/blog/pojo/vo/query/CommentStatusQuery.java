package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description 评论请求
 * @ClassName CommentStatusQuery
 * @Author 23DAY
 * @Date 2023/1/29 21:05
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CommentStatusQuery", description = "评论请求")
public class CommentStatusQuery {

    /**
     * 主键
     */
    @ApiModelProperty(name = "idList", value = "评论id", dataType = "List<Integer>")
    @NotBlank
    private List<Integer> idList;

    /**
     * 是否审核
     */
    @ApiModelProperty(name = "isReview", value = "是否审核", dataType = "Boolean")
    @NotBlank
    private Boolean isReview;

}
