package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Talk;
import site.day.blog.mapper.TalkMapper;
import site.day.blog.service.TalkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Talk服务实现类
 * @ClassName TalkServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {

}
