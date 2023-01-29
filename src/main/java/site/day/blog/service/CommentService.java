package site.day.blog.service;

import site.day.blog.pojo.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.CommentDTO;
import site.day.blog.pojo.vo.query.CommentQuery;
import site.day.blog.pojo.vo.query.CommentReviewQuery;

import java.util.List;

/**
 * @Description Comment服务类
 * @ClassName CommentService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface CommentService extends IService<Comment> {

    List<CommentDTO> getComments(CommentQuery commentQuery);

    void saveComment(CommentQuery commentQuery);

    List<CommentDTO> getRepliesById(Integer id);

    void saveCommentLike(Integer id);

    void updateCommentReview(CommentReviewQuery commentReviewQuery);

    void deleteCommentByIds(CommentReviewQuery commentReviewQuery);
}
