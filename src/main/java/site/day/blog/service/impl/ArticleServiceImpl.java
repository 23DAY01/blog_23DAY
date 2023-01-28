package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.ArticleTagMapper;
import site.day.blog.mapper.CategoryMapper;
import site.day.blog.mapper.TagMapper;
import site.day.blog.pojo.domain.Article;
import site.day.blog.mapper.ArticleMapper;
import site.day.blog.pojo.domain.ArticleTag;
import site.day.blog.pojo.domain.Category;
import site.day.blog.pojo.domain.Tag;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.pojo.vo.ArticlePreviewVO;
import site.day.blog.pojo.vo.query.ArticleConditionQuery;
import site.day.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static site.day.blog.constant.ArticleConst.STATUS_PUBLIC;
import static site.day.blog.constant.ArticleConst.VISITOR_ARTICLE_LIST;
import static site.day.blog.constant.RedisConst.*;

/**
 * @Description Article服务实现类
 * @ClassName ArticleServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HttpSession session;

    /**
     * @Description 通过条件获取文章
     * @Author 23DAY
     * @Date 2023/1/25 14:01
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    @Override
    public List<ArticleDTO> getArticlesByCondition(ArticleConditionQuery query) {

        //分页
        Page<Article> articlePage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        //通过普通条件获取列表
        List<Article> articleList = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                .like(Objects.nonNull(query.getKeyWord()), Article::getArticleTitle, query.getKeyWord())
                .eq(Objects.nonNull(query.getType()), Article::getType, query.getType())
                .eq(Objects.nonNull(query.getStatus()), Article::getStatus, query.getStatus())
                .eq(Objects.nonNull(query.getDeleted()), Article::getDeleted, query.getDeleted())
                .ge(Objects.nonNull(query.getStartTime()), Article::getCreateTime, query.getStartTime())
                .le(Objects.nonNull(query.getStartTime()), Article::getCreateTime, query.getStartTime()));

        //类型转化 do->dto article->articlePreviewDTO
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);

        //获取category和tag的文章 然后进行封装
        //Optional.ofNullable(query.getCategoryId()).ifPresent(categoryId->{});
        if (Objects.nonNull(query.getCategoryId())) {
            Integer categoryId = query.getCategoryId();
            //选取目标分类id
            articleDTOList = articleDTOList.stream()
                    .filter(articleDTO -> Objects.equals(articleDTO.getCategoryId(), categoryId))
                    .collect(Collectors.toList());
            //获取category
            Category category = categoryMapper.selectById(categoryId);
            //注入DTO
            articleDTOList.forEach(articleDTO -> articleDTO.setCategoryName(category.getCategoryName()));
        } else if (Objects.nonNull(query.getTagId())) {
            //通过已筛选过的articleId和目标tagId，筛选出包含目标tagId的articleId，然后获取articleTag
            List<Integer> articleIdList = articleDTOList.stream()
                    .map(ArticleDTO::getId)
                    .collect(Collectors.toList());
            articleIdList = articleTagMapper.selectList(new LambdaQueryWrapper<ArticleTag>()
                            .in(CollectionUtils.isNotEmpty(articleIdList), ArticleTag::getArticleId, articleIdList)
                            .eq(ArticleTag::getTagId, query.getTagId())).stream()
                    .map(ArticleTag::getArticleId)
                    .distinct()
                    .collect(Collectors.toList());
            List<ArticleTag> articleTagList = articleTagMapper.selectList(new LambdaQueryWrapper<ArticleTag>().in(CollectionUtils.isNotEmpty(articleIdList), ArticleTag::getArticleId, articleIdList));
            //获取articleTag中的所有标签
            List<Integer> tagIdList = articleTagList.stream()
                    .map(ArticleTag::getTagId)
                    .collect(Collectors.toList());
            List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<Tag>().in(CollectionUtils.isNotEmpty(tagIdList), Tag::getId, tagIdList));
            //筛选articlePreviewDTOList中符合的articlePreviewDTO，因为articleId经过了第二次过滤
            List<Integer> finalArticleIdList = articleIdList;
            articleDTOList = articleDTOList.stream()
                    .filter(articlePreviewDTO -> emptyIfNull(finalArticleIdList).contains(articlePreviewDTO.getId()))
                    .collect(Collectors.toList());
            //通过articleId到TagId做映射，分别注入
            Map<Integer, List<Integer>> articleId2tagIdMap = articleTagList
                    .stream()
                    .collect(Collectors.groupingBy(ArticleTag::getArticleId, Collectors.mapping(ArticleTag::getTagId, Collectors.toList())));
            for (ArticleDTO articleDTO : articleDTOList) {
                //获取标签列表中当前文章包含的标签
                List<Tag> tagOfArticleDTO = tagList
                        .stream()
                        .filter(tag -> emptyIfNull(articleId2tagIdMap.get(articleDTO.getId())).contains(tag.getId()))
                        .collect(Collectors.toList());
                //类型转换
                List<TagDTO> tagDTOList = mapStruct.tagList2tagDTOList(tagOfArticleDTO);
                //注入
                articleDTO.setTagList(tagDTOList);
            }
        }

        //分页 通过已筛选出来的articleId重新select一遍进行分页
        List<Integer> pageArticleIdList = articleDTOList.stream().map(ArticleDTO::getId).collect(Collectors.toList());
        List<Integer> finalPageArticleIdList = articleMapper.selectPage(articlePage,
                        new LambdaQueryWrapper<Article>()
                                .in(CollectionUtils.isNotEmpty(pageArticleIdList), Article::getId, pageArticleIdList))
                .getRecords()
                .stream()
                .map(Article::getId)
                .collect(Collectors.toList());
        articleDTOList = articleDTOList.stream().filter(articlePreviewDTO -> emptyIfNull(finalPageArticleIdList).contains(articlePreviewDTO.getId())).collect(Collectors.toList());

        //设置分页参数
        PageUtil.setTotal(articlePage.getTotal());
        return articleDTOList;
    }

    /**
     * @Description 获取文章归档
     * @Author 23DAY
     * @Date 2023/1/26 1:45
     * @Param [site.day.blog.pojo.vo.query.PageQuery]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    @Override
    public List<ArticleDTO> getArticles() {
        //分页
        Page<Article> articlePage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Article> articleList = articleMapper.selectPage(articlePage, Wrappers.emptyWrapper()).getRecords();
        //设置分页参数
        PageUtil.setTotal(articlePage.getTotal());
        //类型转换
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //注入dto
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    @Override
    public void saveArticleLike(Integer articleId) {
        String articleUserLikeKey = ARTICLE_USER_LIKE + AuthUtil.getLoginUser().getUserAuthDto().getId();
        if (redisUtil.sIsMember(articleUserLikeKey, articleId)) {
            //如果文章点赞过，则删除点赞记录，并且文章点赞量-1
            redisUtil.sRemove(articleUserLikeKey, articleId);
            redisUtil.hDecr(ARTICLE_LIKE_COUNT, articleId.toString(), 1);
        } else {
            //如果文章没点赞过，则增加点赞记录，并且文章点赞量+1
            redisUtil.sAdd(articleUserLikeKey, articleId);
            redisUtil.hIncr(ARTICLE_LIKE_COUNT, articleId.toString(), 1);
        }
    }

    @Override
    public ArticleDTO getArticleById(Integer id) {
        //异步查询推荐文章
        CompletableFuture<List<ArticleDTO>> recommendArticleList = CompletableFuture.supplyAsync(() -> getRecommendArticles(id));
        //异步查询最新文章
        CompletableFuture<List<ArticleDTO>> newestArticleList = CompletableFuture.supplyAsync(this::getNewestArticles);
        //获取该篇文章
        ArticleDTO articleDTO = mapStruct.Article2ArticleDTO(articleMapper.selectById(id));
        if (Objects.isNull(articleDTO)) throw BusinessException.withErrorCodeEnum(StatusCodeEnum.ARTICLE_MISSING);
        //更新文章访问量
        updateViewCount(id);
        //获取上一篇文章和下一篇文章
        ArticleDTO lastArticle = mapStruct.Article2ArticleDTO(articleMapper.selectOne(Wrappers.lambdaQuery(Article.class)
                .eq(Article::getStatus, STATUS_PUBLIC)
                .lt(Article::getId, id)
                .orderByDesc(Article::getId)
                .last("limit 1")));
        ArticleDTO nextArticle = mapStruct.Article2ArticleDTO(articleMapper.selectOne(Wrappers.lambdaQuery(Article.class)
                .eq(Article::getStatus, STATUS_PUBLIC)
                .gt(Article::getId, id)
                .orderByAsc(Article::getId)
                .last("limit 1")));
        //封装
        try {
            articleDTO.setRecommendArticleList(recommendArticleList.get());
            articleDTO.setNewestArticleList(newestArticleList.get());
        } catch (InterruptedException | ExecutionException e) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.UNKNOWN_RUNTIME_ERROR);
        }
        articleDTO.setLastArticle(lastArticle);
        articleDTO.setNextArticle(nextArticle);
        List<ArticleDTO> articleDTOList = Collections.singletonList(articleDTO);
        addViewAndLikeCount(articleDTOList);
        addDTOFiled(articleDTOList);
        return articleDTOList.get(0);
    }

    /**
     * @Description 更新文章访问量
     * @Author 23DAY
     * @Date 2023/1/27 16:21
     * @Param [java.lang.Integer]
     * @Return void
     **/
    private void updateViewCount(Integer id) {
        //从session中获取访问过的文章id
        List<Integer> articleIdList = CommonUtil.objToList(session.getAttribute(VISITOR_ARTICLE_LIST), Integer.class);
        //如果在一个session没有访问过该文章
        if (articleIdList.contains(id)) {
            articleIdList.add(id);
            redisUtil.zIncr(ARTICLE_VIEW_COUNT, id, 1D);
        }
    }

    /**
     * @Description 获得推荐文章
     * @Author 23DAY
     * @Date 2023/1/27 15:52
     * @Param [java.lang.Integer]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    public List<ArticleDTO> getRecommendArticles(Integer id) {
        //获取该文章的所有标签id 获取标签下的所有文章
        List<Integer> tagIdList = articleTagMapper.selectList(Wrappers.lambdaQuery(ArticleTag.class).eq(ArticleTag::getArticleId, id))
                .stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        List<Integer> articleIdList = articleTagMapper.selectList(Wrappers.lambdaQuery(ArticleTag.class).in(CollectionUtils.isNotEmpty(tagIdList), ArticleTag::getTagId, tagIdList))
                .stream().map(ArticleTag::getArticleId).filter(articleId -> !articleId.equals(id)).collect(Collectors.toList());
        List<Article> articleList = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                .in(CollectionUtils.isNotEmpty(articleIdList), Article::getId, articleIdList)
                .eq(Article::getStatus, STATUS_PUBLIC)
                .orderByDesc(Article::getCreateTime)
                .last("limit 5"));
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //注入dto
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    /**
     * @Description 获得最新文章
     * @Author 23DAY
     * @Date 2023/1/27 15:53
     * @Param []
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    public List<ArticleDTO> getNewestArticles() {
        List<Article> articleList = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                .eq(Article::getStatus, STATUS_PUBLIC)
                .orderByDesc(Article::getCreateTime)
                .last("limit 5"));
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    /**
     * @Description 添加tag信息
     * @Author 23DAY
     * @Date 2023/1/26 17:58
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addTagInfo(List<ArticleDTO> articleDTOList) {
        //文章id
        List<Integer> articleIdList = articleDTOList.stream().map(ArticleDTO::getId).collect(Collectors.toList());
        //获取article tag 关系
        List<ArticleTag> articleTagList = articleTagMapper.selectList(Wrappers.lambdaQuery(ArticleTag.class)
                .in(CollectionUtils.isNotEmpty(articleIdList), ArticleTag::getArticleId, articleIdList));
        List<Integer> tagIdList = articleTagList.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        List<Tag> tagList = tagMapper.selectList(Wrappers.lambdaQuery(Tag.class)
                .in(CollectionUtils.isNotEmpty(tagIdList), Tag::getId, tagIdList));
        Map<Integer, List<Integer>> articleId2tagIdMap = articleTagList.stream().collect(Collectors.groupingBy(ArticleTag::getArticleId, Collectors.mapping(ArticleTag::getTagId, Collectors.toList())));
        for (ArticleDTO articleDTO : articleDTOList) {
            List<Tag> tags = tagList.stream()
                    .filter(tag -> emptyIfNull(articleId2tagIdMap.get(articleDTO.getId())).contains(tag.getId()))
                    .collect(Collectors.toList());
            //类型转换
            List<TagDTO> tagDTOList = mapStruct.tagList2tagDTOList(tags);
            //注入
            articleDTO.setTagList(tagDTOList);
        }
    }

    /**
     * @Description 添加category信息
     * @Author 23DAY
     * @Date 2023/1/26 17:58
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addCategoryInfo(List<ArticleDTO> articleDTOList) {
        List<Integer> categoryIdList = articleDTOList.stream().map(ArticleDTO::getCategoryId).collect(Collectors.toList());
        List<Category> categoryList = categoryMapper.selectList(Wrappers.lambdaQuery(Category.class).in(CollectionUtils.isNotEmpty(categoryIdList), Category::getId, categoryIdList));
        //结果变为映射
        Map<Integer, String> categoryId2Name = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));
        //将name注入
        articleDTOList.forEach(articleDTO -> articleDTO.setCategoryName(categoryId2Name.get(articleDTO.getCategoryId())));
    }

    /**
     * @Description 添加浏览量
     * @Author 23DAY
     * @Date 2023/1/27 15:26
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addViewAndLikeCount(List<ArticleDTO> articleDTOList) {
        articleDTOList.forEach(articleDTO -> {
            Integer articleId = articleDTO.getId();
            articleDTO.setViewCount(redisUtil.zScore(ARTICLE_VIEW_COUNT, articleId).intValue());
            articleDTO.setLikeCount((Integer) redisUtil.hGet(ARTICLE_LIKE_COUNT, String.valueOf(articleId)));
        });
    }

    /**
     * @Description 添加转换dto所需字段
     * @Author 23DAY
     * @Date 2023/1/27 15:27
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addDTOFiled(List<ArticleDTO> articleDTOList) {
        //获取category
        addCategoryInfo(articleDTOList);
        //获取tag
        addTagInfo(articleDTOList);
        //获取浏览量、浏览量
        addViewAndLikeCount(articleDTOList);
    }

    /**
     * @Description 获得文章排行
     * @Author 23DAY
     * @Date 2023/1/27 12:21
     * @Param [java.lang.Integer]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    public List<ArticleDTO> getArticleRank(Integer rankCount) {
        //redis中获取前rankCount的的文章
        Map<Object, Double> articleId2viewCountMap = redisUtil.zReverseRangeWithScore(ARTICLE_VIEW_COUNT, 0, rankCount - 1);
        //获取文章id后查询文章
        List<Integer> articleIdList = articleId2viewCountMap.keySet().stream().map(key -> (Integer) key).collect(Collectors.toList());
        List<Article> articleList = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                .in(CollectionUtils.isNotEmpty(articleIdList), Article::getId, articleIdList));
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //为dto封装viewCount并基于viewCount排序
        articleDTOList.forEach(articleDTO -> articleDTO.setViewCount(articleId2viewCountMap.get(articleDTO.getId()).intValue()));
        articleDTOList = articleDTOList.stream().sorted(Comparator.comparingInt(ArticleDTO::getViewCount).reversed()).collect(Collectors.toList());
        return articleDTOList;
    }

}

