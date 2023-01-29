package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.dto.BlogInfoDTO;
import site.day.blog.pojo.dto.WebsiteConfigDTO;
import site.day.blog.pojo.vo.BlogBackInfoVO;
import site.day.blog.pojo.vo.WebsiteConfigVO;
import site.day.blog.pojo.vo.query.WebsiteConfigQuery;
import site.day.blog.service.BlogService;
import site.day.blog.service.WebsiteConfigService;
import site.day.blog.service.impl.BlogServiceImpl;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName AdminBlogController
 * @Author 23DAY
 * @Date 2023/1/29 11:26
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MapStruct mapStruct;

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


}
