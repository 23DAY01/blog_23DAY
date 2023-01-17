package site.day.blog.service.impl;

import site.day.blog.pojo.domain.ChatRecord;
import site.day.blog.mapper.ChatRecordMapper;
import site.day.blog.service.ChatRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天记录表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements ChatRecordService {

}
