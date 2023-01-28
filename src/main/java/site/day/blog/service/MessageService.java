package site.day.blog.service;

import site.day.blog.pojo.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.MessageDTO;
import site.day.blog.pojo.vo.query.MessageQuery;

import java.util.List;

/**
 * @Description Message服务类
 * @ClassName MessageService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface MessageService extends IService<Message> {

    void saveMessage(MessageQuery messageQuery);

    List<MessageDTO> getMessages();
}
