package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.day.blog.annotation.OptLog;
import site.day.blog.constant.OptTypeConst;
import site.day.blog.pojo.dto.CommentDTO;
import site.day.blog.pojo.dto.FriendLinkDTO;
import site.day.blog.pojo.dto.MenuDTO;
import site.day.blog.pojo.dto.MessageDTO;
import site.day.blog.pojo.vo.*;
import site.day.blog.pojo.vo.query.*;
import site.day.blog.service.*;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import java.util.List;

import static site.day.blog.constant.OptTypeConst.*;

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
    private FriendLinkService friendLinkService;

    @Autowired
    private MessageService messageService;

    @OptLog(optType = UPDATE)
    @ApiOperation("审核评论")
    @PostMapping("/comments/status")
    public ResponseAPI<?> updateCommentStatus(
            @ApiParam(name = "commentReviewQuery", value = "评论审核")
            @RequestBody
            @Valid
                    CommentStatusQuery commentStatusQuery) {
        commentService.updateCommentStatus(commentStatusQuery);
        return ResponseAPI.success();
    }

    @OptLog(optType = REMOVE)
    @ApiOperation("删除评论")
    @PostMapping("/comments/delete")
    public ResponseAPI<?> deleteComment(
            @ApiParam(name = "commentReviewQuery", value = "删除评论")
            @RequestBody
            @Valid
                    CommentStatusQuery commentStatusQuery) {
        commentService.deleteCommentByIds(commentStatusQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("查询后台评论")
    @GetMapping("/comments/list")
    public ResponseAPI<?> getBackComments(
            @ApiParam(name = "commentQuery", value = "评论查询")
                    CommentQuery commentQuery,
            @ApiParam(name = "pageQuery", value = "分页条件")
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

    @OptLog(optType = SAVE_OR_UPDATE)
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

    @OptLog(optType = REMOVE)
    @ApiOperation("删除友链")
    @PostMapping("/links/delete")
    public ResponseAPI<?> deleteFriendLink(
            @ApiParam(name = "linkIdList", value = "删除友链id")
            @RequestBody
                    List<Integer> linkIdList) {
        friendLinkService.deleteFriendLink(linkIdList);
        return ResponseAPI.success();
    }

    @ApiOperation("后台查看留言")
    @GetMapping("/messages/list")
    public ResponseAPI<?> getBackMessages(
            @ApiParam(name = "isReview", value = "是否审核")
                    Boolean isReview,
            @ApiParam(name = "pageQuery", value = "分页条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<MessageDTO> messageDTOList = messageService.getBackMessages(isReview);
        List<MessageBackVO> messageBackVOList = mapStruct.MessageDTOList2MessageBackVOList(messageDTOList);
        return ResponseAPI.success(PageResult.build(messageBackVOList));
    }

    @OptLog(optType = UPDATE)
    @ApiOperation("审核评论")
    @PostMapping("/messages/status")
    public ResponseAPI<?> updateMessagesStatus(
            @ApiParam(name = "messageStatusQuery", value = "审核评论")
            @Valid
            @RequestBody
                    MessageStatusQuery messageStatusQuery) {
        messageService.updateMessagesStatus(messageStatusQuery);
        return ResponseAPI.success();
    }

    @OptLog(optType = REMOVE)
    @ApiOperation("删除留言")
    @PostMapping("/messages/delete")
    public ResponseAPI<?> deleteMessage(
            @ApiParam(name = "messageIdList", value = "删除留言")
            @RequestBody
                    List<Integer> messageIdList) {
        messageService.deleteMessageByIds(messageIdList);
        return ResponseAPI.success();
    }

}
