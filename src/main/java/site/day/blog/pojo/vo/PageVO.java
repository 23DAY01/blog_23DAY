package site.day.blog.pojo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @ClassName PageVO
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "PageVO", description = "页面表")
public class PageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页面id
     */
    @ApiModelProperty(name = "id", value = "页面id", dataType = "Integer")
    private Integer id;

    /**
     * 页面名称
     */
    @ApiModelProperty(name = "pageName", value = "页面名称", dataType = "String")
    private String pageName;

    /**
     * 页面标签
     */
    @ApiModelProperty(name = "pageLabel", value = "页面标签", dataType = "String")
    private String pageLabel;

    /**
     * 页面封面
     */
    @ApiModelProperty(name = "pageCover", value = "页面封面", dataType = "String")
    private String pageCover;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createTime;



}
