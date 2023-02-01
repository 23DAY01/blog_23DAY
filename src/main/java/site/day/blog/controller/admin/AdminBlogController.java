package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.dto.*;
import site.day.blog.pojo.vo.*;
import site.day.blog.pojo.vo.query.MenuSaveQuery;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.pojo.vo.query.PageSaveQuery;
import site.day.blog.pojo.vo.query.WebsiteConfigQuery;
import site.day.blog.service.BlogService;
import site.day.blog.service.MenuService;
import site.day.blog.service.OperationLogService;
import site.day.blog.service.PageService;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.naming.spi.ResolveResult;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @ClassName AdminBlogController
 * @Author 23DAY
 * @Date 2023/1/29 11:26
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员-博客模块")
@RestController
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PageService pageService;

    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation("查看后台博客信息")
    @GetMapping("/info")
    public ResponseAPI<?> getBlogBackInfo() {
        BlogInfoDTO blogInfo = blogService.getBlogInfo();
        BlogBackInfoVO blogBackInfoVO = mapStruct.BlogInfoDTO2BlogBackInfoVO(blogInfo);
        return ResponseAPI.success(blogBackInfoVO);
    }

    @ApiOperation("上传博客配置图片")
    @PostMapping("/upload/image")
    public ResponseAPI<?> saveArticleImage(
            @ApiParam(name = "file", value = "配置图片")
                    MultipartFile file) {
        String url = blogService.uploadArticleImage(file);
        return ResponseAPI.success(url);
    }

    @ApiOperation("更新网站配置")
    @PostMapping("/config/update")
    public ResponseAPI<?> updateWebsiteConfig(
            @ApiParam(name = "websiteConfigQuery", value = "更新网站配置")
            @RequestBody
                    WebsiteConfigQuery websiteConfigQuery) {
        blogService.updateWebsiteConfig(websiteConfigQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("获取网站配置")
    @GetMapping("/config")
    public ResponseAPI<?> getWebsiteConfig() {
        WebsiteConfigDTO websiteConfigDTO = blogService.getWebsiteConfig();
        WebsiteConfigVO websiteConfigVO = mapStruct.WebsiteConfigDTO2WebsiteConfigVO(websiteConfigDTO);
        return ResponseAPI.success(websiteConfigVO);
    }

    @ApiOperation("查询操作日志")
    @GetMapping("/logs/list")
    public ResponseAPI<?> getOperationLogs(
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<OperationLogDTO> operationLogDTOList = operationLogService.getOperationLogs();
        List<OperationLogVO> operationLogVOList = mapStruct.OperationLogDTOList2OperationLogVOList(operationLogDTOList);
        return ResponseAPI.success(PageResult.build(operationLogVOList));
    }

    @ApiOperation("删除操作日志")
    @PostMapping("/logs/delete")
    public ResponseAPI<?> deleteOperationLog(
            @ApiParam(name = "logIdList", value = "删除日志id")
            @RequestBody
                    List<Integer> logIdList) {
        operationLogService.deleteOperationLog(logIdList);
        return ResponseAPI.success();
    }

    @ApiOperation("查询菜单")
    @GetMapping("/menus/list")
    public ResponseAPI<?> getMenus() {
        List<MenuDTO> menuDTOList = menuService.getMenus();
        List<MenuVO> menuVOList = mapStruct.MenuDTOList2MenuVOList(menuDTOList);
        return ResponseAPI.success(menuVOList);
    }

    @ApiOperation("添加菜单")
    @PostMapping("/menus/save")
    public ResponseAPI<?> saveOrUpdateMenu(
            @ApiParam(name = "menuSaveQuery", value = "添加菜单")
            @Valid
            @RequestBody
                    MenuSaveQuery menuSaveQuery) {
        menuService.saveOrUpdateMenu(menuSaveQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("删除菜单")
    @GetMapping("/menus/{id}/delete")
    public ResponseAPI<?> deleteMenu(
            @ApiParam(name = "id", value = "菜单id")
            @PathVariable
                    Integer id) {
        menuService.deleteMenuById(id);
        return ResponseAPI.success();
    }

    @ApiOperation("获取页面")
    @GetMapping("/pages/list")
    public ResponseAPI<?> getPages() {
        List<PageDTO> pageDTOList = pageService.getPages();
        List<PageVO> pageVOList = mapStruct.PageDTOList2PageVOList(pageDTOList);
        return ResponseAPI.success(pageVOList);
    }

    @ApiOperation("添加页面")
    @PostMapping("/pages/save")
    public ResponseAPI<?> saveOrUpdatePage(
            @ApiParam(name = "pageSaveQuery", value = "添加页面")
            @Valid
            @RequestBody
                    PageSaveQuery pageSaveQuery) {
        pageService.saveOrUpdatePage(pageSaveQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("删除页面")
    @GetMapping("/pages/{id}/delete")
    public ResponseAPI<?> deletePage(
            @ApiParam(name = "id", value = "页面id")
            @PathVariable
                    Integer id) {
        pageService.deletePageById(id);
        return ResponseAPI.success();
    }


}
