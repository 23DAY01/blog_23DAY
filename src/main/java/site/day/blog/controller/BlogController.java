package site.day.blog.controller;


import org.elasticsearch.client.license.LicensesStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.day.blog.pojo.dto.BlogInfoDTO;
import site.day.blog.pojo.vo.BlogHomeInfoVO;
import site.day.blog.service.BlogService;
import site.day.blog.service.WebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.service.impl.BlogServiceImpl;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import java.util.List;

/**
 * @Description WebsiteConfig控制器
 * @ClassName WebsiteConfigController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "websiteConfig模块")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private WebsiteConfigService websiteConfigService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询websiteConfig", notes = "根据id查询websiteConfig")
    @GetMapping("/websiteConfigs/{id}")
    public ResponseAPI<?> getWebsiteConfigById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(websiteConfigService.getById(id));
    }

    /**
     * @Description 查看首页博客信息
     * @Author 23DAY
     * @Date 2023/1/27 13:08
     * @Param []
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看博客信息")
    @GetMapping("/info")
    public ResponseAPI<?> getBlogHomeInfo() {
        BlogInfoDTO blogInfo = blogService.getBlogInfo();
        BlogHomeInfoVO blogHomeInfoVO = mapStruct.BlogInfoDTO2BlogHomeInfoVO(blogInfo);
        return ResponseAPI.success(blogHomeInfoVO);
    }

    /**
     * @Description 查看关于我
     * @Author 23DAY
     * @Date 2023/1/27 13:46
     * @Param []
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看关于我的信息")
    @GetMapping("/about")
    public ResponseAPI<?> getAbout() {
        return ResponseAPI.success(blogService.getBlogInfo().getAbout());
    }

}
