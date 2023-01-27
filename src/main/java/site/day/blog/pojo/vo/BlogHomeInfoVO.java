package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.day.blog.pojo.dto.*;

import java.util.List;

/**
 * @Description
 * @ClassName BlogHomeInfo
 * @Author 23DAY
 * @Date 2023/1/27 12:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("博客首页信息")
public class BlogHomeInfoVO {

    /**
     * 文章数量
     */
    @ApiModelProperty(name = "articleCount", value = "文章数量", dataType = "Integer")
    private Integer articleCount;

    /**
     * 分类数量
     */
    @ApiModelProperty(name = "categoryCount", value = "分类数量", dataType = "Integer")
    private Integer categoryCount;

    /**
     * 标签数量
     */
    @ApiModelProperty(name = "tagCount", value = "标签数量", dataType = "Integer")
    private Integer tagCount;

    /**
     * 浏览量总数
     */
    @ApiModelProperty(name = "viewCount", value = "浏览量总数", dataType = "Integer")
    private Integer viewCount;

    /**
     * 页面
     */
    @ApiModelProperty(name = "pageList", value = "页面", dataType = "List<PageVO>")
    private List<PageVO> pageList;

    /**
     * 网站配置
     */
    @ApiModelProperty(name = "websiteConfig", value = "网站配置", dataType = "WebsiteConfigDTO")
    private WebsiteConfigDTO websiteConfig;

    /**
     * 关于我
     */
    @ApiModelProperty(name = "about", value = "关于我", dataType = "String")
    private String about;

}
