package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.enums.FileExtEnum;
import site.day.blog.enums.FilePathEnum;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.*;
import site.day.blog.pojo.domain.*;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.pojo.vo.query.ArticleConditionQuery;
import site.day.blog.pojo.vo.query.ArticleSaveQuery;
import site.day.blog.pojo.vo.query.ArticleStatusQuery;
import site.day.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.service.CategoryService;
import site.day.blog.strategy.context.UploadStrategyContext;
import site.day.blog.utils.*;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static site.day.blog.constant.ArticleConst.STATUS_PUBLIC;
import static site.day.blog.constant.ArticleConst.VISITOR_ARTICLE_LIST;
import static site.day.blog.constant.CommonConst.DEFAULT_WEBSITE_CONFIG_ID;
import static site.day.blog.constant.RedisConst.*;

/**
 * @Description Article???????????????
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

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Cacheable(cacheNames = "article", sync = true)
    @Override
    public List<ArticleDTO> getArticlesByCondition(ArticleConditionQuery query) {
        //??????
        Page<Article> articlePage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        //????????????????????????articleId
        List<Integer> articleIdList1 = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                        .like(Objects.nonNull(query.getKeyWord()), Article::getArticleTitle, query.getKeyWord())
                        .eq(Objects.nonNull(query.getType()), Article::getType, query.getType())
                        .eq(Objects.nonNull(query.getStatus()), Article::getStatus, query.getStatus())
                        .ge(Objects.nonNull(query.getStartTime()), Article::getCreateTime, query.getStartTime())
                        .le(Objects.nonNull(query.getEndTime()), Article::getCreateTime, query.getEndTime())
                        .orderByDesc(Article::getCreateTime))
                .stream().map(Article::getId).collect(Collectors.toList());
        //??????category??????articleId
        List<Integer> articleIdList2 = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                        .eq(Objects.nonNull(query.getCategoryId()), Article::getCategoryId, query.getCategoryId()))
                .stream().map(Article::getId).collect(Collectors.toList());
        //??????tag??????articleId
        List<Integer> articleIdList3 = articleTagMapper.selectList(Wrappers.lambdaQuery(ArticleTag.class)
                        .eq(Objects.nonNull(query.getTagId()), ArticleTag::getTagId, query.getTagId()))
                .stream().map(ArticleTag::getArticleId).distinct().collect(Collectors.toList());
        //??????
        Collection<Integer> articleIdList = CollectionUtils.intersection(articleIdList1, CollectionUtils.intersection(articleIdList2, articleIdList3));
        //????????????
        List<Article> articleList = articleMapper.selectPage(articlePage, Wrappers.lambdaQuery(Article.class)
                .in(CollectionUtils.isNotEmpty(articleIdList), Article::getId, articleIdList)).getRecords();
        PageUtil.setTotal(articlePage.getTotal());
        //???????????? do->dto article->articleDTO
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //??????dto
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    @Cacheable(cacheNames = "article", sync = true)
    @Override
    public List<ArticleDTO> getArticles() {
        //??????
        Page<Article> articlePage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Article> articleList = articleMapper.selectPage(articlePage, Wrappers.lambdaQuery(Article.class)
                .orderByDesc(Article::getIsTop)
                .orderByDesc(Article::getCreateTime)).getRecords();
        //??????????????????
        PageUtil.setTotal(articlePage.getTotal());
        //????????????
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //??????dto
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    @Override
    public void saveArticleLike(Integer articleId) {
        String articleUserLikeKey = ARTICLE_USER_LIKE + AuthUtil.getLoginUser().getUserAuth().getId();
        if (redisUtil.sIsMember(articleUserLikeKey, articleId)) {
            //?????????????????????????????????????????????????????????????????????-1
            redisUtil.sRemove(articleUserLikeKey, articleId);
            redisUtil.hDecr(ARTICLE_LIKE_COUNT, articleId.toString(), 1);
        } else {
            //????????????????????????????????????????????????????????????????????????+1
            redisUtil.sAdd(articleUserLikeKey, articleId);
            redisUtil.hIncr(ARTICLE_LIKE_COUNT, articleId.toString(), 1);
        }
    }

    @Cacheable(cacheNames = "article", sync = true)
    @Override
    public ArticleDTO getArticleById(Integer id) {
        //????????????????????????
        CompletableFuture<List<ArticleDTO>> recommendArticleList = CompletableFuture.supplyAsync(() -> getRecommendArticles(id));
        //????????????????????????
        CompletableFuture<List<ArticleDTO>> newestArticleList = CompletableFuture.supplyAsync(this::getNewestArticles);
        //??????????????????
        ArticleDTO articleDTO = mapStruct.Article2ArticleDTO(articleMapper.selectById(id));
        if (Objects.isNull(articleDTO)) throw BusinessException.withErrorCodeEnum(StatusCodeEnum.ARTICLE_MISSING);
        //?????????????????????
        updateViewCount(id);
        //???????????????????????????????????????
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
        //??????
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

    @Cacheable(cacheNames = "article", sync = true)
    @Override
    public List<ArticleDTO> getBackArticles(ArticleConditionQuery query) {
        //??????
        Page<Article> articlePage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());

        //????????????????????????articleId
        List<Integer> articleIdList1 = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                        .like(Objects.nonNull(query.getKeyWord()), Article::getArticleTitle, query.getKeyWord())
                        .eq(Objects.nonNull(query.getType()), Article::getType, query.getType())
                        .eq(Objects.nonNull(query.getStatus()), Article::getStatus, query.getStatus())
                        .eq(Objects.nonNull(query.getDeleted()), Article::getDeleted, query.getDeleted())
                        .ge(Objects.nonNull(query.getStartTime()), Article::getCreateTime, query.getStartTime())
                        .le(Objects.nonNull(query.getEndTime()), Article::getCreateTime, query.getEndTime())
                        .orderByDesc(Article::getCreateTime))
                .stream().map(Article::getId).collect(Collectors.toList());
        //??????category??????articleId
        List<Integer> articleIdList2 = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                        .eq(Objects.nonNull(query.getCategoryId()), Article::getCategoryId, query.getCategoryId()))
                .stream().map(Article::getId).collect(Collectors.toList());
        //??????tag??????articleId
        List<Integer> articleIdList3 = articleTagMapper.selectList(Wrappers.lambdaQuery(ArticleTag.class)
                        .eq(Objects.nonNull(query.getTagId()), ArticleTag::getTagId, query.getTagId()))
                .stream().map(ArticleTag::getArticleId).distinct().collect(Collectors.toList());
        //??????
        Collection<Integer> articleIdList = CollectionUtils.intersection(articleIdList1, CollectionUtils.intersection(articleIdList2, articleIdList3));
        //????????????
        List<Article> articleList = articleMapper.selectPage(articlePage, Wrappers.lambdaQuery(Article.class)
                .in(CollectionUtils.isNotEmpty(articleIdList), Article::getId, articleIdList)).getRecords();
        PageUtil.setTotal(articlePage.getTotal());
        //???????????? do->dto article->articleDTO
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //??????dto
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    @CachePut(cacheNames = "article")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateArticle(ArticleSaveQuery articleSaveQuery) {
        Article article = mapStruct.ArticleSaveQuery2Article(articleSaveQuery);
        article.setUserId(AuthUtil.getLoginUser().getUserInfo().getId());
        //???????????????????????????????????????
        Integer categoryId = categoryService.saveArticleCategory(articleSaveQuery.getCategoryName());
        article.setCategoryId(categoryId);
        //????????????
        saveOrUpdate(article);
        //???????????????????????????????????????
        tagService.saveArticleTag(article.getId(), articleSaveQuery.getTagNameList());
    }

    @CachePut(cacheNames = "article")
    @Override
    public void updateArticleStatus(ArticleStatusQuery articleStatusQuery) {
        Article article = mapStruct.ArticleStatusQuery2Article(articleStatusQuery);
        articleMapper.updateById(article);
    }

    @CacheEvict(cacheNames = "article", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticle(Integer id) {
        articleTagMapper.delete(Wrappers.lambdaQuery(ArticleTag.class).eq(ArticleTag::getArticleId, id));
        articleMapper.deleteById(id);
    }

    @Cacheable(cacheNames = "article", sync = true)
    @Override
    public ArticleDTO getBackArticleById(Integer id) {
        Article article = articleMapper.selectById(id);
        ArticleDTO articleDTO = mapStruct.Article2ArticleDTO(article);
        List<ArticleDTO> articleDTOList = Collections.singletonList(articleDTO);
        addDTOFiled(articleDTOList);
        return articleDTOList.get(0);
    }

    @Override
    public List<String> exportArticles(List<Integer> articleIdList) {
        List<Article> articleList = articleMapper.selectBatchIds(articleIdList);
        ArrayList<String> urlList = new ArrayList<>();
        for (Article article : articleList) {
            try {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(article.getArticleContent().getBytes(StandardCharsets.UTF_8));
                String url = uploadStrategyContext.executeUploadStrategy(article.getArticleTitle() + FileExtEnum.MD.getExtName(), inputStream, FilePathEnum.MD.getPath());
                urlList.add(url);
            } catch (Exception e) {
                throw BusinessException.withErrorCodeEnum(StatusCodeEnum.FILE_UPLOAD_ERROR);
            }
        }
        return urlList;
    }

    @Override
    public String uploadArticleImage(MultipartFile file) {
        return uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.ARTICLE.getPath());
    }


    /**
     * @Description ?????????????????????
     * @Author 23DAY
     * @Date 2023/1/27 16:21
     * @Param [java.lang.Integer]
     * @Return void
     **/
    private void updateViewCount(Integer id) {
        //???session???????????????????????????id
        List<Integer> articleIdList = ConvertUtil.objToList(session.getAttribute(VISITOR_ARTICLE_LIST), Integer.class);
        //???????????????session????????????????????????
        if (articleIdList.contains(id)) {
            articleIdList.add(id);
            redisUtil.zIncr(ARTICLE_VIEW_COUNT, id, 1D);
        }
    }

    /**
     * @Description ??????????????????
     * @Author 23DAY
     * @Date 2023/1/27 15:52
     * @Param [java.lang.Integer]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    @Cacheable(cacheNames = "article", sync = true)
    public List<ArticleDTO> getRecommendArticles(Integer id) {
        //??????????????????????????????id ??????????????????????????????
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
        //??????dto
        addDTOFiled(articleDTOList);
        return articleDTOList;
    }

    /**
     * @Description ??????????????????
     * @Author 23DAY
     * @Date 2023/1/27 15:53
     * @Param []
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    @Cacheable(cacheNames = "article", sync = true)
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
     * @Description ??????tag??????
     * @Author 23DAY
     * @Date 2023/1/26 17:58
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addTagInfo(List<ArticleDTO> articleDTOList) {
        //??????id
        List<Integer> articleIdList = articleDTOList.stream().map(ArticleDTO::getId).collect(Collectors.toList());
        //??????article tag ??????
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
            //????????????
            List<TagDTO> tagDTOList = mapStruct.tagList2tagDTOList(tags);
            //??????
            articleDTO.setTagList(tagDTOList);
        }
    }

    /**
     * @Description ??????category??????
     * @Author 23DAY
     * @Date 2023/1/26 17:58
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addCategoryInfo(List<ArticleDTO> articleDTOList) {
        List<Integer> categoryIdList = articleDTOList.stream().map(ArticleDTO::getCategoryId).collect(Collectors.toList());
        List<Category> categoryList = categoryMapper.selectList(Wrappers.lambdaQuery(Category.class).in(CollectionUtils.isNotEmpty(categoryIdList), Category::getId, categoryIdList));
        //??????????????????
        Map<Integer, String> categoryId2Name = categoryList.stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));
        //???name??????
        articleDTOList.forEach(articleDTO -> articleDTO.setCategoryName(categoryId2Name.get(articleDTO.getCategoryId())));
    }

    /**
     * @Description ???????????????
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
     * @Description ????????????dto????????????
     * @Author 23DAY
     * @Date 2023/1/27 15:27
     * @Param [java.util.List<site.day.blog.pojo.dto.ArticleDTO>]
     * @Return void
     **/
    public void addDTOFiled(List<ArticleDTO> articleDTOList) {
        //??????category
        addCategoryInfo(articleDTOList);
        //??????tag
        addTagInfo(articleDTOList);
        //???????????????????????????
        addViewAndLikeCount(articleDTOList);
    }

    /**
     * @Description ??????????????????
     * @Author 23DAY
     * @Date 2023/1/27 12:21
     * @Param [java.lang.Integer]
     * @Return java.util.List<site.day.blog.pojo.dto.ArticleDTO>
     **/
    @Cacheable(cacheNames = "article", sync = true)
    public List<ArticleDTO> getArticleRank(Integer rankCount) {
        //redis????????????rankCount????????????
        Map<Object, Double> articleId2viewCountMap = redisUtil.zReverseRangeWithScore(ARTICLE_VIEW_COUNT, 0, rankCount - 1);
        //????????????id???????????????
        List<Integer> articleIdList = articleId2viewCountMap.keySet().stream().map(key -> (Integer) key).collect(Collectors.toList());
        List<Article> articleList = articleMapper.selectList(Wrappers.lambdaQuery(Article.class)
                .in(CollectionUtils.isNotEmpty(articleIdList), Article::getId, articleIdList)
                .orderByDesc(Article::getCreateTime));
        List<ArticleDTO> articleDTOList = mapStruct.ArticleList2ArticleDTOList(articleList);
        //???dto??????viewCount?????????viewCount??????
        articleDTOList.forEach(articleDTO -> articleDTO.setViewCount(articleId2viewCountMap.get(articleDTO.getId()).intValue()));
        articleDTOList = articleDTOList.stream().sorted(Comparator.comparingInt(ArticleDTO::getViewCount).reversed()).collect(Collectors.toList());
        return articleDTOList;
    }

}

