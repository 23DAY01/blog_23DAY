package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Message;
import site.day.blog.mapper.MessageMapper;
import site.day.blog.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Message服务实现类
 * @ClassName MessageServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
