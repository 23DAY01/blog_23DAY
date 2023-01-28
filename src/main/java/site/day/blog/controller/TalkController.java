package site.day.blog.controller;

import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.dto.TalkDTO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.TalkHomeVO;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.service.TalkService;
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
 * @Description Talk控制器
 * @ClassName TalkController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "talk模块")
@RestController
@RequestMapping("/talks")
public class TalkController {

    @Autowired
    public TalkService talkService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("根据id获取说说")
    @GetMapping("/{id}")
    public ResponseAPI<?> getTalkById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        TalkDTO talkDTO = talkService.getTalkById(id);
        TalkHomeVO talkHomeVO = mapStruct.TalkDTO2TalkHomeVO(talkDTO);
        return ResponseAPI.success(talkHomeVO);
    }

    /**
     * @Description 查看说说
     * @Author 23DAY
     * @Date 2023/1/28 20:37
     * @Param [site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看说说")
    @GetMapping("/list")
    public ResponseAPI<?> getTalks(
            @ApiParam(name = "articleConditionQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<TalkDTO> talkDTOList = talkService.getTalks();
        List<TalkHomeVO> talkHomeVOList = mapStruct.TalkDTOList2TalkHomeVOList(talkDTOList);
        return ResponseAPI.success(PageResult.build(talkHomeVOList));
    }

    /**
     * @Description 查看首页说说内容
     * @Author 23DAY
     * @Date 2023/1/28 21:21
     * @Param []
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看首页说说内容")
    @GetMapping("/home")
    public ResponseAPI<?> getHomeTalkContent() {
        List<String> contents = talkService.getHomeTalkContent();
        return ResponseAPI.success(contents);
    }

    /**
     * @Description 说说点赞
     * @Author 23DAY
     * @Date 2023/1/28 21:54
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("说说点赞")
    @GetMapping("/{id}/like")
    public ResponseAPI<?> saveTalkLike(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id){
        talkService.saveTalkLike(id);
        return ResponseAPI.success();
    }


}
