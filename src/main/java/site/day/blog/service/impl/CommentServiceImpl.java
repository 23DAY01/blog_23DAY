package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Comment;
import site.day.blog.mapper.CommentMapper;
import site.day.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Comment服务实现类
 * @ClassName CommentServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
