package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.domain.Menu;
import site.day.blog.pojo.domain.Talk;
import site.day.blog.pojo.dto.CommentDTO;
import site.day.blog.pojo.dto.FriendLinkDTO;
import site.day.blog.pojo.vo.*;
import site.day.blog.pojo.vo.query.CommentQuery;
import site.day.blog.pojo.vo.query.CommentReviewQuery;
import site.day.blog.pojo.vo.query.FriendLinkSaveQuery;
import site.day.blog.pojo.vo.query.PageQuery;
import site.day.blog.service.*;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @ClassName AdminFunctionController
 * @Author 23DAY
 * @Date 2023/1/29 21:00
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员-功能模块")
@RestController
@RequestMapping("/admin/")
public class AdminFunctionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private TalkService talkService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private MessageService messageService;

    @ApiOperation("审核评论")
    @PostMapping("/comments/review")
    public ResponseAPI<?> updateCommentReview(
            @ApiParam(name = "commentReviewQuery", value = "评论审核")
            @RequestBody
            @Valid
                    CommentReviewQuery commentReviewQuery) {
        commentService.updateCommentReview(commentReviewQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("删除评论")
    @PostMapping("/comments/delete")
    public ResponseAPI<?> deleteComment(
            @ApiParam(name = "commentReviewQuery", value = "评论审核")
            @RequestBody
            @Valid
                    CommentReviewQuery commentReviewQuery) {
        commentService.deleteCommentByIds(commentReviewQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("查询后台评论")
    @GetMapping("/comments/list")
    public ResponseAPI<?> getBackComments(
            @ApiParam(name = "commentQuery", value = "评论查询")
                    CommentQuery commentQuery,
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<CommentDTO> commentDTOList = commentService.getComments(commentQuery);
        List<CommentBackVO> commentBackVOList = mapStruct.CommentDTOList2CommentBackVOList(commentDTOList);
        return ResponseAPI.success(PageResult.build(commentBackVOList));
    }

    @ApiOperation("查询友链")
    @GetMapping("/links/list")
    public ResponseAPI<?> getFriendLinks() {
        List<FriendLinkDTO> friendLinkDTOList = friendLinkService.getFriendLinks();
        List<FriendLinkBackVO> friendLinkBackVOList = mapStruct.FriendLinkDTOList2FriendLinkBackVOList(friendLinkDTOList);
        return ResponseAPI.success(friendLinkBackVOList);
    }

    @ApiOperation("添加友链")
    @PostMapping("/links/save")
    public ResponseAPI<?> saveOrUpdateFriendLink(
            @ApiParam(name = "friendLinkSaveQuery", value = "添加友链")
            @Valid
            @RequestBody
                    FriendLinkSaveQuery friendLinkSaveQuery) {
        friendLinkService.saveOrUpdateFriendLink(friendLinkSaveQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("删除友链")
    @PostMapping("/links/delete")
    public ResponseAPI<?> deleteFriendLink(
            @ApiParam(name = "linkIdList", value = "删除友链id")
            @RequestBody
                    List<Integer> linkIdList) {
        friendLinkService.deleteFriendLink(linkIdList);
        return ResponseAPI.success();
    }


}
