package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.day.blog.pojo.dto.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @ClassName BlogBackInfo
 * @Author 23DAY
 * @Date 2023/1/27 12:18
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("博客后台信息")
public class BlogBackInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 分类列表
     */
    @ApiModelProperty(name = "categoryList", value = "分类列表", dataType = "List<CategoryVO>")
    private List<CategoryHomeVO> categoryList;

    /**
     * 标签数量
     */
    @ApiModelProperty(name = "tagCount", value = "标签数量", dataType = "Integer")
    private Integer tagCount;

    /**
     * 标签列表
     */
    @ApiModelProperty(name = "tagList", value = "标签列表", dataType = "List<TagVO>")
    private List<TagHomeVO> tagList;

    /**
     * 浏览量总数
     */
    @ApiModelProperty(name = "viewCount", value = "浏览量总数", dataType = "Integer")
    private Integer viewCount;

    /**
     * 浏览量
     */
    @ApiModelProperty(name = "viewList", value = "浏览量", dataType = "List<ViewVO>")
    private List<ViewVO> viewList;

    /**
     * 页面
     */
    @ApiModelProperty(name = "pageList", value = "页面", dataType = "List<PageVO>")
    private List<PageVO> pageList;

    /**
     * 留言数量
     */
    @ApiModelProperty(name = "messageCount", value = "留言数量", dataType = "Integer")
    private Integer messageCount;

    /**
     * 用户数量
     */
    @ApiModelProperty(name = "userCount", value = "用户数量", dataType = "Integer")
    private Integer userCount;

    /**
     * 网站配置
     */
    @ApiModelProperty(name = "websiteConfig", value = "网站配置", dataType = "WebsiteConfigDTO")
    private WebsiteConfigDTO websiteConfig;

    /**
     * 文章排行
     */
    @ApiModelProperty(name = "articleRankList", value = "文章排行", dataType = "List<ArticleRankVO>")
    private List<ArticleRankVO> articleRankList;

    /**
     * 关于我
     */
    @ApiModelProperty(name = "about", value = "关于我", dataType = "String")
    private String about;


}
