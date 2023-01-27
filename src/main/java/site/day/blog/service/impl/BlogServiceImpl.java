package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.day.blog.mapper.*;
import site.day.blog.pojo.domain.Article;
import site.day.blog.pojo.dto.*;
import site.day.blog.service.BlogService;
import site.day.blog.service.ViewService;
import site.day.blog.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static site.day.blog.constant.ArticleConst.STATUS_PUBLIC;
import static site.day.blog.constant.CommonConst.*;
import static site.day.blog.constant.RedisConst.*;

/**
 * @Description
 * @ClassName BlogServiceImpl
 * @Author 23DAY
 * @Date 2023/1/26 20:32
 * @Version 1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WebsiteConfigMapper websiteConfigMapper;

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ViewService viewService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public BlogInfoDTO getBlogInfo() {
        //查询文章数量
        Integer articleCount = Math.toIntExact(articleMapper.selectCount(Wrappers.lambdaQuery(Article.class).eq(Article::getStatus, STATUS_PUBLIC)));
        //查询分类和分类数量
        List<CategoryDTO> categoryDTOList = mapStruct.CategoryList2CategoryDTOList(categoryMapper.selectList(Wrappers.emptyWrapper()));
        Integer categoryCount = categoryDTOList.size();
        //查询标签和标签数量
        List<TagDTO> tagDTOList = mapStruct.tagList2tagDTOList(tagMapper.selectList(Wrappers.emptyWrapper()));
        Integer tagCount = tagDTOList.size();
        //查询访问量
        Integer viewCount = (Integer) Optional.ofNullable(redisUtil.get(BLOG_VIEW_COUNT)).orElse(0);
        //查询网站配置
        WebsiteConfigDTO websiteConfigDTO = mapStruct.WebsiteConfig2WebsiteConfigDTO(websiteConfigMapper.selectById(DEFAULT_WEBSITE_CONFIG_ID));
        List<PageDTO> pageDTOList = mapStruct.PageList2PageDTOList(pageMapper.selectList(Wrappers.emptyWrapper()));
        //查询留言量
        Integer messageCount = Math.toIntExact(messageMapper.selectCount(Wrappers.emptyWrapper()));
        //查询用户量
        Integer userCount = Math.toIntExact(userInfoMapper.selectCount(Wrappers.emptyWrapper()));
        //查询一周浏览量
        List<ViewDTO> viewDTOList = viewService.getViewsDuring(7);
        //查询浏览量前5名的文章
        List<ArticleDTO> articleDTOList = articleService.getArticleRank(5);
        return BlogInfoDTO.builder()
                .articleCount(articleCount)
                .categoryList(categoryDTOList)
                .categoryCount(categoryCount)
                .tagList(tagDTOList)
                .tagCount(tagCount)
                .viewCount(viewCount)
                .websiteConfig(websiteConfigDTO)
                .pageList(pageDTOList)
                .messageCount(messageCount)
                .userCount(userCount)
                .viewList(viewDTOList)
                .articleRankList(articleDTOList)
                .build();
    }

    @Override
    public void reportVisitor() {
        //获取ip
        String ipAddress = WebUtil.getIpAddress(request);
        //获取设备
        UserAgent userAgent = WebUtil.getUserAgent(request);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        //md5过的唯一key
        String md5 = EncryptUtil.md5((ipAddress + browser.getName() + operatingSystem.getName()).getBytes());
        if (!redisUtil.sIsMember(BLOG_VISITOR, md5)) {
            String ipSource = WebUtil.getIpSource(ipAddress);
            if (!StringUtil.isNullOrEmpty(ipSource)) {
                ipSource = ipSource.substring(0, 2).replaceAll(PROVINCE, "").replaceAll(CITY, "");
                redisUtil.hIncr(VISITOR_AREA, ipSource, 1L);
            }
        }
        redisUtil.incr(BLOG_VIEW_COUNT, 1);
        redisUtil.sAdd(BLOG_VISITOR, md5);
    }
}