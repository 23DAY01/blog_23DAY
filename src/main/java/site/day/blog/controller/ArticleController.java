package site.day.blog.controller;

import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.vo.ArchiveVO;
import site.day.blog.pojo.vo.ArticleHomeVO;
import site.day.blog.pojo.vo.ArticlePreviewVO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.query.ArticleConditionQuery;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description Article控制器
 * @ClassName ArticleController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "article模块")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询article", notes = "根据id查询article")
    @GetMapping("/{id}")
    public ResponseAPI<?> getArticleById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        List<ArticleDTO> articleDTOList = articleService.getArticleById(id);

        return ResponseAPI.success();
    }

    /**
     * @Description 根据条件查询article
     * @Author 23DAY
     * @Date 2023/1/26 18:26
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据条件查询article")
    @GetMapping("/condition")
    public ResponseAPI<?> getArticlesByCondition(
            @ApiParam(name = "articleConditionQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    ArticleConditionQuery articleConditionQuery) {
        //调用业务逻辑获得dto
        List<ArticleDTO> articlePreviewDTOList = articleService.getArticlesByCondition(articleConditionQuery);
        //dto转换成vo
        List<ArticlePreviewVO> articlePreviewVOList = mapStruct.articleDTOList2articlePreviewVOList(articlePreviewDTOList);
        //返回分页结果
        return ResponseAPI.success(PageResult.build(articlePreviewVOList));
    }

    /**
     * @Description 查看文章归档
     * @Author 23DAY
     * @Date 2023/1/26 18:26
     * @Param [site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看文章归档")
    @GetMapping("/archives")
    public ResponseAPI<?> getArchives(
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<ArticleDTO> articleDTOList = articleService.getArticles();
        List<ArchiveVO> archiveVOList = mapStruct.ArticleDTOList2ArchiveVOList(articleDTOList);
        //分页结果
        return ResponseAPI.success(PageResult.build(archiveVOList));
    }

    /**
     * @Description 查看首页文章
     * @Author 23DAY
     * @Date 2023/1/26 18:27
     * @Param [site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("首页查看文章")
    @GetMapping("")
    public ResponseAPI<?> getHomeArticles(
            @ApiParam(name = "articleConditionQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<ArticleDTO> articleDTOList = articleService.getArticles();
        List<ArticleHomeVO> articleHomeVOList = mapStruct.ArticleDTOList2ArticleHomeVOList(articleDTOList);
        return ResponseAPI.success(PageResult.build(articleHomeVOList));
    }

    /**
     * @Description 关键字查询
     * @Author 23DAY
     * @Date 2023/1/26 18:31
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    //TODO 采用查询策略
    @ApiOperation("关键字查询")
    @GetMapping("/search")
    public ResponseAPI<?> getArticleBySearch(
            @ApiParam(name = "articleConditionQuery", value = "根据关键字查询")
            @Valid
            @RequestParam(required = false)
                    ArticleConditionQuery articleConditionQuery) {
        return null;
    }

    /**
     * @Description 文章点赞
     * @Author 23DAY
     * @Date 2023/1/26 18:35
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("文章点赞")
    @GetMapping("/like/{articleId}")
    public ResponseAPI<?> saveArticleLike(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable("articleId")
                    Integer articleId) {
        articleService.saveArticleLike(articleId);
        return ResponseAPI.success();
    }


}
