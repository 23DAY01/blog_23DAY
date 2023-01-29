package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.dto.BlogInfoDTO;
import site.day.blog.pojo.dto.OperationLogDTO;
import site.day.blog.pojo.dto.WebsiteConfigDTO;
import site.day.blog.pojo.vo.BlogBackInfoVO;
import site.day.blog.pojo.vo.OperationLogVO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.WebsiteConfigVO;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.pojo.vo.query.WebsiteConfigQuery;
import site.day.blog.service.BlogService;
import site.day.blog.service.OperationLogService;
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


}
