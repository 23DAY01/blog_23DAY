package site.day.blog.controller;

import site.day.blog.service.WebsiteConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.ResponseAPI;

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
public class WebsiteConfigController {

    @Autowired
    public WebsiteConfigService websiteConfigService;

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

}
