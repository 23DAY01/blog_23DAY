package site.day.blog.service;

import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.dto.ArticlePreviewDTO;
import site.day.blog.pojo.vo.query.ArticleConditionQuery;
import site.day.blog.pojo.vo.query.ArticleSaveQuery;
import site.day.blog.pojo.vo.query.ArticleStatusQuery;
import site.day.blog.pojo.vo.query.PageQuery;

import java.util.List;

/**
 * @Description Article服务类
 * @ClassName ArticleService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface ArticleService extends IService<Article> {

    /**
     * @Description 通过条件获取文章
     * @Author 23DAY
     * @Date 2023/1/25 14:01
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    List<ArticleDTO> getArticlesByCondition(ArticleConditionQuery articleConditionQuery);

    /**
     * @Description 获取文章归档
     * @Author 23DAY
     * @Date 2023/1/26 1:45
     * @Param [site.day.blog.pojo.vo.query.PageQuery]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    List<ArticleDTO> getArticles();

    /**
     * @Description 文章点赞
     * @Author 23DAY
     * @Date 2023/2/3 15:07
     * @Param [java.lang.Integer]
     * @Return void
     **/
    void saveArticleLike(Integer articleId);

    /**
     * @Description 根据id获取文章
     * @Author 23DAY
     * @Date 2023/2/3 15:09
     * @Param [java.lang.Integer]
     * @Return site.day.blog.pojo.dto.ArticleDTO
     **/
    ArticleDTO getArticleById(Integer id);

    /**
     * @Description 查看后台文章
     * @Author 23DAY
     * @Date 2023/1/29 15:20
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    List<ArticleDTO> getBackArticles(ArticleConditionQuery articleConditionQuery);

    /**
     * @Description 添加或修改文章
     * @Author 23DAY
     * @Date 2023/1/29 18:03
     * @Param [site.day.blog.pojo.vo.query.ArticleSaveQuery]
     * @Return void
     **/
    void saveOrUpdateArticle(ArticleSaveQuery articleSaveQuery);

    /**
     * @Description 更新文章状态
     * @Author 23DAY
     * @Date 2023/2/3 15:10
     * @Param [site.day.blog.pojo.vo.query.ArticleStatusQuery]
     * @Return void
     **/
    void updateArticleStatus(ArticleStatusQuery articleStatusQuery);

    /**
     * @Description 删除文章
     * @Author 23DAY
     * @Date 2023/2/3 15:10
     * @Param [java.lang.Integer]
     * @Return void
     **/
    void deleteArticle(Integer id);

    /**
     * @Description 根据id获取后台文章
     * @Author 23DAY
     * @Date 2023/2/3 15:11
     * @Param [java.lang.Integer]
     * @Return site.day.blog.pojo.dto.ArticleDTO
     **/
    ArticleDTO getBackArticleById(Integer id);

    /**
     * @Description 导出文章
     * @Author 23DAY
     * @Date 2023/2/3 15:11
     * @Param [java.util.List<java.lang.Integer>]
     * @Return java.util.List<java.lang.String>
     **/
    List<String> exportArticles(List<Integer> articleIdList);

    /**
     * @Description 上传文章
     * @Author 23DAY
     * @Date 2023/2/3 15:11
     * @Param [org.springframework.web.multipart.MultipartFile]
     * @Return java.lang.String
     **/
    String uploadArticleImage(MultipartFile file);
}
