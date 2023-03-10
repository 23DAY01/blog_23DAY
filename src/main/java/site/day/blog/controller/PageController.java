package site.day.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import site.day.blog.service.PageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.ResponseAPI;

/**
 * @Description Page控制器
 * @ClassName PageController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "page模块")
@RestController
public class PageController {

    @Autowired
    public PageService pageService;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询page", notes = "根据id查询page")
    @GetMapping("/pages/{id}")
    public ResponseAPI<?> getPageById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(pageService.getById(id));
    }

}
