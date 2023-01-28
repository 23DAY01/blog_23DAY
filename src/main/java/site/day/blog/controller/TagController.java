package site.day.blog.controller;

import org.elasticsearch.common.util.Maps;
import org.springframework.web.bind.annotation.PathVariable;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.pojo.vo.TagVO;
import site.day.blog.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import java.util.List;
import java.util.Map;

/**
 * @Description Tag控制器
 * @ClassName TagController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "tag模块")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询tag", notes = "根据id查询tag")
    @GetMapping("/tags/{id}")
    public ResponseAPI<?> getTagById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(tagService.getById(id));
    }

    /**
     * @Description 获取标签
     * @Author 23DAY
     * @Date 2023/1/28 19:49
     * @Param []
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("获取标签")
    @GetMapping("/list")
    public ResponseAPI<?> getTags() {
        List<TagDTO> tagDTOList = tagService.getTags();
        List<TagVO> tagVOList = mapStruct.tagDTOList2tagVOList(tagDTOList);
        return ResponseAPI.success(tagVOList);
    }

}
