package site.day.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import site.day.blog.pojo.domain.View;
import site.day.blog.pojo.vo.WebsiteConfigVO;

import java.util.List;

/**
 * @Description
 * @ClassName BlogInfoDTO
 * @Author 23DAY
 * @Date 2023/1/26 20:37
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogInfoDTO {

    /**
     * 文章数量
     */
    private Integer articleCount;

    /**
     * 分类数量
     */
    private Integer categoryCount;

    /**
     * 分类列表
     */
    private List<CategoryDTO> categoryList;

    /**
     * 标签数量
     */
    private Integer tagCount;

    /**
     * 标签列表
     */
    private List<TagDTO> tagList;

    /**
     * 浏览量总数
     */
    private Integer viewCount;

    /**
     * 浏览量
     */
    private List<ViewDTO> viewList;

    /**
     * 页面
     */
    private List<PageDTO> pageList;

    /**
     * 留言数量
     */
    private Integer messageCount;

    /**
     * 用户数量
     */
    private Integer userCount;

    /**
     * 网站配置
     */
    private WebsiteConfigDTO websiteConfig;

    /**
     * 文章排行
     */
    private List<ArticleDTO> articleRankList;

    /**
     * 关于我
     */
    private String about;


}
