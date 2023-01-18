package site.day.blog.controller;

import site.day.blog.service.RoleResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.ResponseAPI;

/**
 * @Description RoleResource控制器
 * @ClassName RoleResourceController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "roleResource模块")
@RestController
public class RoleResourceController {

    @Autowired
    public RoleResourceService roleResourceService;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询roleResource", notes = "根据id查询roleResource")
    @GetMapping("/roleResources/{id}")
    public ResponseAPI<?> getRoleResourceById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(roleResourceService.getById(id));
    }

}
