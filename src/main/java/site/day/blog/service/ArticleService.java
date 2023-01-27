package site.day.blog.service;

import site.day.blog.pojo.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.dto.ArticlePreviewDTO;
import site.day.blog.pojo.vo.query.ArticleConditionQuery;
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
}
