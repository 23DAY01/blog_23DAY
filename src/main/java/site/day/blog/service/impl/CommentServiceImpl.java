package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.pojo.domain.Comment;
import site.day.blog.mapper.CommentMapper;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.pojo.dto.CommentDTO;
import site.day.blog.pojo.vo.query.CommentQuery;
import site.day.blog.pojo.vo.query.CommentStatusQuery;
import site.day.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.AuthUtil;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.PageUtil;
import site.day.blog.utils.RedisUtil;

import java.util.*;
import java.util.stream.Collectors;

import static site.day.blog.constant.RedisConst.COMMENT_LIKE_COUNT;
import static site.day.blog.constant.RedisConst.COMMENT_USER_LIKE;

/**
 * @Description Comment服务实现类
 * @ClassName CommentServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<CommentDTO> getComments(CommentQuery commentQuery) {
        //通过query分页查询出顶级comment
        Page<Comment> commentPage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Comment> commentList = commentMapper.selectPage(commentPage, Wrappers.lambdaQuery(Comment.class)
                .eq(Objects.nonNull(commentQuery.getAffiliationId()), Comment::getAffiliationId, commentQuery.getAffiliationId())
                .eq(Objects.nonNull(commentQuery.getType()), Comment::getType, commentQuery.getType())
                .isNull(Comment::getParentId)
                .eq(Comment::getIsReview, true)).getRecords();
        //设置分页total
        PageUtil.setTotal(commentPage.getTotal());
        //如果文章没有评论直接返回
        if (commentList.isEmpty()) return new ArrayList<CommentDTO>();
        //类型转换
        List<CommentDTO> commentDTOList = mapStruct.CommentList2CommentDTOList(commentList);
        //获取各个顶级评论的回复评论
        for (CommentDTO commentDTO : commentDTOList) {
            List<CommentDTO> replyCommentDTOList = mapStruct.CommentList2CommentDTOList(commentMapper.selectList(Wrappers.lambdaQuery(Comment.class)
                    .eq(Comment::getTopId, commentDTO.getId())
                    .eq(Comment::getIsReview, true)));
            addUserInfo(replyCommentDTOList);
            addLikeCount(replyCommentDTOList);
            commentDTO.setReplyCount(replyCommentDTOList.size())
                    .setReplyList(replyCommentDTOList);
        }
        //封装信息
        addUserInfo(commentDTOList);
        addLikeCount(commentDTOList);
        return commentDTOList;
    }

    /**
     * @Description 添加评论
     * @Author 23DAY
     * @Date 2023/1/28 16:47
     * @Param [site.day.blog.pojo.vo.query.CommentQuery]
     * @Return void
     **/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(CommentQuery commentQuery) {
        Comment comment = Comment.builder()
                .userId(AuthUtil.getLoginUser().getUserInfo().getId())
                .affiliationId(commentQuery.getAffiliationId())
                .type(commentQuery.getType())
                .commentContent(commentQuery.getCommentContent())
                .isReview(false)
                .build();
        //如果父评论id为null则是顶级评论
        if (Objects.isNull(commentQuery.getParentId())) {
            //使用雪花算法生成主键 顶级评论id与自身id相同
            Integer id = Math.toIntExact(IdWorker.getId());
            comment.setId(id)
                    .setTopId(id);
        } else {
            //如果不为null则是对评论的回复
            comment.setTopId(commentQuery.getTopId())
                    .setParentId(commentQuery.getParentId())
                    .setReplyUserId(commentQuery.getReplyUserId());
        }
        commentMapper.insert(comment);
    }

    @Override
    public List<CommentDTO> getRepliesById(Integer id) {
        Page<Comment> commentPage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Comment> commentList = commentMapper.selectPage(commentPage, Wrappers.lambdaQuery(Comment.class)
                .eq(Comment::getTopId, id)).getRecords();
        PageUtil.setTotal(commentPage.getTotal());
        List<CommentDTO> commentDTOList = mapStruct.CommentList2CommentDTOList(commentList);
        addLikeCount(commentDTOList);
        addUserInfo(commentDTOList);
        return commentDTOList;
    }

    @Override
    public void saveCommentLike(Integer id) {
        //用户点赞key
        String commentLikeKey = COMMENT_USER_LIKE + AuthUtil.getLoginUser().getUserInfo().getId();
        if (redisUtil.sIsMember(commentLikeKey, id)) {
            //如果点过赞则在列表中去掉该评论id  并且评论点赞量-1
            redisUtil.sRemove(commentLikeKey, id);
            redisUtil.hDecr(COMMENT_LIKE_COUNT, id.toString(), 1);
        } else {
            //如果没点过，则加入列表，并且评论点赞量+1
            redisUtil.sAdd(commentLikeKey, id);
            redisUtil.hIncr(COMMENT_LIKE_COUNT, id.toString(), 1);
        }
    }

    @Override
    public void updateCommentStatus(CommentStatusQuery query) {
        List<Comment> commentList = query.getIdList().stream().map(id -> Comment.builder()
                        .id(id)
                        .isReview(query.getIsReview())
                        .build())
                .collect(Collectors.toList());
        updateBatchById(commentList);
    }

    @Override
    public void deleteCommentByIds(CommentStatusQuery commentStatusQuery) {
        removeBatchByIds(commentStatusQuery.getIdList());
    }

    /**
     * @Description 为dto增加likeCount
     * @Author 23DAY
     * @Date 2023/1/28 15:46
     * @Param [java.util.List<site.day.blog.pojo.dto.CommentDTO>]
     * @Return void
     **/
    public void addLikeCount(List<CommentDTO> commentDTOList) {
        //获取点赞量
        Map<String, Object> commentLikeMap = (Map<String, Object>) (Object) redisUtil.hGetAll(COMMENT_LIKE_COUNT);
        commentDTOList.forEach(commentDTO -> commentDTO.setLikeCount((Integer) commentLikeMap.get(commentDTO.getId().toString())));
    }

    /**
     * @Description 增加用户相关信息
     * @Author 23DAY
     * @Date 2023/1/28 15:47
     * @Param [java.util.List<site.day.blog.pojo.dto.CommentDTO>]
     * @Return void
     **/
    public void addUserInfo(List<CommentDTO> commentDTOList) {
        for (CommentDTO commentDTO : commentDTOList) {
            //增加当前评论用户信息
            UserInfo userInfo = userInfoMapper.selectById(commentDTO.getUserId());
            commentDTO.setAvatar(userInfo.getAvatar())
                    .setWebsite(userInfo.getWebsite())
                    .setNickName(userInfo.getNickname());
            //增加被回复用户信息
            if (Objects.nonNull(commentDTO.getUserId())) {
                UserInfo replyUserInfo = userInfoMapper.selectById(commentDTO.getUserId());
                commentDTO.setReplyWebsite(replyUserInfo.getWebsite())
                        .setReplyAvatar(replyUserInfo.getAvatar())
                        .setReplyNickname(replyUserInfo.getNickname());
            }
        }
    }
}
