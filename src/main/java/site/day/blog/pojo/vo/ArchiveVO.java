package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description 文章归档
 * @ClassName ArchiveVO
 * @Author 23DAY
 * @Date 2023/1/25 19:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文章预览")
public class ArchiveVO {

    @ApiModelProperty(name = "id",value = "文章id",dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "articleTitle",value = "文章标题",dataType = "String")
    private String articleTitle;

    @ApiModelProperty(name = "createTime",value = "创建时间",dataType = "LocalDateTime")
    private LocalDateTime createTime;


}
