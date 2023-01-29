package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.enums.FilePathEnum;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.vo.ArticleBackVO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.query.ArticleConditionQuery;
import site.day.blog.pojo.vo.query.ArticleSaveQuery;
import site.day.blog.pojo.vo.query.ArticleStatusQuery;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.service.ArticleService;
import site.day.blog.strategy.context.UploadStrategyContext;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @ClassName AdminController
 * @Author 23DAY
 * @Date 2023/1/23 21:19
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin/articles")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    /**
     * @Description 查看后台文章
     * @Author 23DAY
     * @Date 2023/1/29 15:43
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery, site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看后台文章")
    @GetMapping("")
    public ResponseAPI<?> getBackArticles(
            @ApiParam(name = "articleConditionQuery", value = "查询条件")
            @Valid
                    ArticleConditionQuery articleConditionQuery,
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<ArticleDTO> articleDTOList = articleService.getBackArticles(articleConditionQuery);
        List<ArticleBackVO> articleBackVOList = mapStruct.ArticleDTOList2ArticleBackVOList(articleDTOList);
        return ResponseAPI.success(PageResult.build(articleBackVOList));
    }

    @ApiOperation("新增或修改文章")
    @PostMapping("/save")
    public ResponseAPI<?> saveArticle(
            @ApiParam(name = "articleSaveQuery", value = "新增或修改文章")
            @Valid
            @RequestBody
                    ArticleSaveQuery articleSaveQuery) {
        articleService.saveOrUpdateArticle(articleSaveQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("修改文章状态")
    @PostMapping("/status")
    public ResponseAPI<?> updateArticleStatus(
            @ApiParam(name = "articleStatusQuery", value = "文章状态修改")
            @Valid
            @RequestBody
                    ArticleStatusQuery articleStatusQuery) {
        articleService.updateArticleStatus(articleStatusQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("上传图片")
    @PostMapping("/upload/image")
    public ResponseAPI<?> uploadArticleImage(
            @ApiParam(name = "file", value = "配置图片")
                    MultipartFile file) {
        String url = articleService.uploadArticleImage(file);
        return ResponseAPI.success(url);
    }

    @ApiOperation("删除文章")
    @GetMapping("/{id}/delete")
    public ResponseAPI<?> deleteArticle(
            @ApiParam(name = "id", value = "文章id")
            @PathVariable
                    Integer id) {
        articleService.deleteArticle(id);
        return ResponseAPI.success();
    }


    @ApiOperation("通过id获取文章")
    @GetMapping("/{id}")
    public ResponseAPI<?> getArticleById(
            @ApiParam(name = "id", value = "文章id")
            @PathVariable
                    Integer id) {
        ArticleDTO articleDTO = articleService.getBackArticleById(id);
        ArticleBackVO articleBackVO = mapStruct.ArticleDTO2ArticleBackVO(articleDTO);
        return ResponseAPI.success(articleBackVO);
    }

    @ApiOperation("导出文章")
    @PostMapping("/export")
    public ResponseAPI<?> exportArticles(
            @RequestBody
                    List<Integer> articleIdList) {
        List<String> urlList = articleService.exportArticles(articleIdList);
        return ResponseAPI.success(urlList);
    }

}
