package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.annotation.OptLog;
import site.day.blog.pojo.dto.ArticleDTO;
import site.day.blog.pojo.dto.CategoryDTO;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.pojo.vo.ArticleBackVO;
import site.day.blog.pojo.vo.CategoryBackVO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.TagBackVO;
import site.day.blog.pojo.vo.query.*;
import site.day.blog.service.ArticleService;
import site.day.blog.service.CategoryService;
import site.day.blog.service.TagService;
import site.day.blog.strategy.context.UploadStrategyContext;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static site.day.blog.constant.OptTypeConst.*;

/**
 * @Description
 * @ClassName AdminController
 * @Author 23DAY
 * @Date 2023/1/23 21:19
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员-文章模块")
@RestController
@RequestMapping("/admin")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * @Description 查看后台文章
     * @Author 23DAY
     * @Date 2023/1/29 15:43
     * @Param [site.day.blog.pojo.vo.query.ArticleConditionQuery, site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看后台文章")
    @GetMapping("/articles/list")
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

    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation("新增或修改文章")
    @PostMapping("/articles/save")
    public ResponseAPI<?> saveArticle(
            @ApiParam(name = "articleSaveQuery", value = "新增或修改文章")
            @Valid
            @RequestBody
                    ArticleSaveQuery articleSaveQuery) {
        articleService.saveOrUpdateArticle(articleSaveQuery);
        return ResponseAPI.success();
    }

    @OptLog(optType = UPDATE)
    @ApiOperation("修改文章状态")
    @PostMapping("/articles/status")
    public ResponseAPI<?> updateArticleStatus(
            @ApiParam(name = "articleStatusQuery", value = "文章状态修改")
            @Valid
            @RequestBody
                    ArticleStatusQuery articleStatusQuery) {
        articleService.updateArticleStatus(articleStatusQuery);
        return ResponseAPI.success();
    }

    @OptLog(optType = UPLOAD)
    @ApiOperation("上传图片")
    @PostMapping("/articles/upload/image")
    public ResponseAPI<?> uploadArticleImage(
            @ApiParam(name = "file", value = "配置图片")
                    MultipartFile file) {
        String url = articleService.uploadArticleImage(file);
        return ResponseAPI.success(url);
    }

    @OptLog(optType = REMOVE)
    @ApiOperation("删除文章")
    @GetMapping("/articles/{id}/delete")
    public ResponseAPI<?> deleteArticle(
            @ApiParam(name = "id", value = "文章id")
            @PathVariable
            @NotBlank
                    Integer id) {
        articleService.deleteArticle(id);
        return ResponseAPI.success();
    }


    @ApiOperation("通过id获取文章")
    @GetMapping("/articles/{id}")
    public ResponseAPI<?> getArticleById(
            @ApiParam(name = "id", value = "文章id")
            @PathVariable
            @NotBlank
                    Integer id) {
        ArticleDTO articleDTO = articleService.getBackArticleById(id);
        ArticleBackVO articleBackVO = mapStruct.ArticleDTO2ArticleBackVO(articleDTO);
        return ResponseAPI.success(articleBackVO);
    }

    @ApiOperation("导出文章")
    @PostMapping("/articles/export")
    public ResponseAPI<?> exportArticles(
            @RequestBody
            @NotBlank
                    List<Integer> articleIdList) {
        List<String> urlList = articleService.exportArticles(articleIdList);
        return ResponseAPI.success(urlList);
    }

    @ApiOperation("获取后台分类")
    @GetMapping("/categories/list")
    public ResponseAPI<?> getBackCategories(
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();
        List<CategoryBackVO> categoryBackVOList = mapStruct.CategoryDTOList2CategoryBackVOList(categoryDTOList);
        return ResponseAPI.success(PageResult.build(categoryBackVOList));
    }

    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation("添加或修改分类")
    @PostMapping("/categories/save")
    public ResponseAPI<?> saveOrUpdateCategory(
            @ApiParam(name = "categorySaveQuery", value = "分类")
            @Valid
            @RequestBody
                    CategorySaveQuery categorySaveQuery) {
        categoryService.saveOrUpdateCategory(categorySaveQuery);
        return ResponseAPI.success();
    }

    @OptLog(optType = REMOVE)
    @ApiOperation("删除分类")
    @PostMapping("/categories/delete")
    public ResponseAPI<?> deleteCategory(
            @ApiParam(name = "idList", value = "删除分类id")
            @RequestBody
            @NotBlank
                    List<Integer> idList) {
        categoryService.deleteCategoryByIds(idList);
        return ResponseAPI.success();
    }

    @ApiOperation("获取后台标签")
    @GetMapping("/tags/list")
    public ResponseAPI<?> getBackTag(
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<TagDTO> tagDTOList = tagService.getBackTags();
        List<TagBackVO> tagBackVOList = mapStruct.TagDTOList2TagBackVOList(tagDTOList);
        return ResponseAPI.success(PageResult.build(tagBackVOList));
    }

    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation("添加标签")
    @PostMapping("/tags/save")
    public ResponseAPI<?> saveOrUpdateTag(
            @ApiParam(name = "tagSaveQuery", value = "添加标签")
            @Valid
            @RequestBody
                    TagSaveQuery tagSaveQuery) {
        tagService.saveOrUpdateTag(tagSaveQuery);
        return ResponseAPI.success();
    }

    @OptLog(optType = REMOVE)
    @ApiOperation("删除标签")
    @PostMapping("/tags/delete")
    public ResponseAPI<?> deleteTag(
            @ApiParam(name = "idList", value = "删除标签")
            @RequestBody
            @NotBlank
                    List<Integer> idList) {
        tagService.deleteTagByIds(idList);
        return ResponseAPI.success();
    }

}
