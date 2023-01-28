package site.day.blog.controller;

import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.dto.CategoryDTO;
import site.day.blog.pojo.vo.CategoryHomeVO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.service.CategoryService;
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
 * @Description Category控制器
 * @ClassName CategoryController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "category模块")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询category", notes = "根据id查询category")
    @GetMapping("/categorys/{id}")
    public ResponseAPI<?> getCategoryById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(categoryService.getById(id));
    }

    @ApiOperation(value = "查看全部分类")
    @GetMapping("/list")
    public ResponseAPI<?> getCategories(
            @ApiParam(name = "articleConditionQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<CategoryDTO> categoryDTOList = categoryService.getCategories();
        List<CategoryHomeVO> categoryHomeVOList = mapStruct.CategoryDTOList2CategoryHomeVOList(categoryDTOList);
        return ResponseAPI.success(PageResult.build(categoryHomeVOList));
    }


}
