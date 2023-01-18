package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Article;
import site.day.blog.mapper.ArticleMapper;
import site.day.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Article服务实现类
 * @ClassName ArticleServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
