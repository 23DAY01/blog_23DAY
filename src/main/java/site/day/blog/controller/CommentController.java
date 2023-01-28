package site.day.blog.controller;

import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.dto.CommentDTO;
import site.day.blog.pojo.vo.CommentHomeVO;
import site.day.blog.pojo.vo.PageResult;
import site.day.blog.pojo.vo.query.CommentQuery;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.PageUtil;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Description Comment控制器
 * @ClassName CommentController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "comment模块")
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    public CommentService commentService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据条件获取评论
     * @Author 23DAY
     * @Date 2023/1/28 16:09
     * @Param [site.day.blog.pojo.vo.query.CommentQuery, site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("获取评论")
    @GetMapping("/list")
    public ResponseAPI<?> getComments(
            @ApiParam(name = "commentQuery", value = "评论查询")
                    CommentQuery commentQuery,
            @ApiParam(name = "pageQuery", value = "分页")
            @Valid
                    PageQuery pageQuery) {
        List<CommentDTO> commentDTOList = commentService.getComments(commentQuery);
        List<CommentHomeVO> commentHomeVOList = mapStruct.CommentDTOList2CommentHomeVOList(commentDTOList);
        return ResponseAPI.success(PageResult.build(commentHomeVOList));
    }

    /**
     * @Description 添加评论
     * @Author 23DAY
     * @Date 2023/1/28 16:48
     * @Param [site.day.blog.pojo.vo.query.CommentQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("添加评论")
    @PostMapping("/save")
    public ResponseAPI<?> saveComment(
            @ApiParam(name = "commentQuery", value = "评论查询")
            @RequestBody
                    CommentQuery commentQuery) {
        commentService.saveComment(commentQuery);
        return ResponseAPI.success();
    }

    /**
     * @Description 查询评论的回复
     * @Author 23DAY
     * @Date 2023/1/28 16:57
     * @Param [java.lang.Integer, site.day.blog.pojo.vo.query.PageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查询评论的回复")
    @GetMapping("/{id}/replies")
    public ResponseAPI<?> getRepliesById(
            @ApiParam(name = "id", value = "评论id")
            @PathVariable
                    Integer id,
            @ApiParam(name = "pageQuery", value = "分页")
            @Valid
                    PageQuery pageQuery) {
        List<CommentDTO> commentDTOList = commentService.getRepliesById(id);
        List<CommentHomeVO> commentHomeVOList = mapStruct.CommentDTOList2CommentHomeVOList(commentDTOList);
        return ResponseAPI.success(PageResult.build(commentHomeVOList));
    }

    /**
     * @Description 评论点赞
     * @Author 23DAY
     * @Date 2023/1/28 16:58
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("评论点赞")
    @GetMapping("{/id}/like")
    public ResponseAPI<?> saveCommentLike(
            @ApiParam(name = "id", value = "评论id")
            @PathVariable
                    Integer id) {
        commentService.saveCommentLike(id);
        return ResponseAPI.success();
    }


}
