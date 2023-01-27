package site.day.blog.service;

import site.day.blog.pojo.dto.BlogInfoDTO;

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

}
