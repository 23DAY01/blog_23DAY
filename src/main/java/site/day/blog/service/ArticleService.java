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


    List<ArticleDTO> getArticlesByCondition(ArticleConditionQuery articleConditionQuery);

    List<ArticleDTO> getArticles();

    void saveArticleLike(Integer articleId);

    ArticleDTO getArticleById(Integer id);

    List<ArticleDTO> getBackArticles(ArticleConditionQuery articleConditionQuery);

    void saveOrUpdateArticle(ArticleSaveQuery articleSaveQuery);

    void updateArticleStatus(ArticleStatusQuery articleStatusQuery);

    void deleteArticle(Integer id);

    ArticleDTO getBackArticleById(Integer id);

    List<String> exportArticles(List<Integer> articleIdList);

    String uploadArticleImage(MultipartFile file);
}
