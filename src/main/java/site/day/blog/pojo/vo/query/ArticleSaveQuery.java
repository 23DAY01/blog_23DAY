package site.day.blog.pojo.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 文章新增或修改
 * @ClassName ArticleSaveQuery
 * @Author 23DAY
 * @Date 2023/1/29 15:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ArticleSaveQuery", description = "文章新增或修改")
public class ArticleSaveQuery {

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不为空")
    @ApiModelProperty(name = "articleTitle",value = "文章标题",dataType = "String")
    private String articleTitle;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    @ApiModelProperty(name = "articleContent", value = "文章内容", required = true, dataType = "String")
    private String articleContent;

    /**
     * 文章封面
     */
    @ApiModelProperty(name = "articleCover", value = "文章封面", dataType = "String")
    private String articleCover;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类不能为空")
    @ApiModelProperty(name = "categoryName", value = "分类名称", dataType = "String")
    private String categoryName;

    /**
     * 标签名称列表
     */
    @NotEmpty(message = "标签不能为空")
    @ApiModelProperty(name = "tagNameList", value = "标签名称列表", dataType = "List<String>")
    private List<String> tagNameList;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(name = "status", value = "状态", dataType = "Integer")
    private Integer status;

    /**
     * 文章类型
     */
    @NotNull(message = "文章类型不能为空")
    @ApiModelProperty(name = "type", value = "文章类型", dataType = "Integer")
    private Integer type;

    /**
     * 原文链接
     */
    @ApiModelProperty(name = "originalUrl", value = "原文链接", dataType = "String")
    private String originalUrl;

    /**
     * 是否置顶
     */
    @NotNull(message = "置顶状态不能为空")
    @ApiModelProperty(name = "isTop", value = "是否置顶", dataType = "Integer")
    private Boolean isTop;


}
