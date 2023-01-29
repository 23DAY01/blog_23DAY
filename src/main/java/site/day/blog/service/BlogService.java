package site.day.blog.service;

import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.dto.BlogInfoDTO;
import site.day.blog.pojo.dto.WebsiteConfigDTO;
import site.day.blog.pojo.vo.query.WebsiteConfigQuery;

import java.util.List;

/**
 * @Description
 * @ClassName BlogService
 * @Author 23DAY
 * @Date 2023/1/26 20:32
 * @Version 1.0
 */
public interface BlogService {

    BlogInfoDTO getBlogInfo();

    void reportVisitor();

    String uploadArticleImage(MultipartFile file);

    void updateWebsiteConfig(WebsiteConfigQuery websiteConfigQuery);

    WebsiteConfigDTO getWebsiteConfig();
}
