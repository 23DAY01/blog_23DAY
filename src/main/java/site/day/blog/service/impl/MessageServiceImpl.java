package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.pojo.domain.Message;
import site.day.blog.mapper.MessageMapper;
import site.day.blog.pojo.dto.MessageDTO;
import site.day.blog.pojo.vo.query.MessageSaveQuery;
import site.day.blog.pojo.vo.query.MessageStatusQuery;
import site.day.blog.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.PageUtil;
import site.day.blog.utils.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description Message服务实现类
 * @ClassName MessageServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private HttpServletRequest request;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 添加留言
     * @Author 23DAY
     * @Date 2023/1/28 19:34
     * @Param [site.day.blog.pojo.vo.query.MessageQuery]
     * @Return void
     **/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveMessage(MessageSaveQuery messageSaveQuery) {
        String ipAddress = WebUtil.getIpAddress(request);
        String ipSource = WebUtil.getIpSource(ipAddress);
        Message message = Message.builder()
                .nickname(messageSaveQuery.getNickname())
                .avatar(messageSaveQuery.getAvatar())
                .messageContent(messageSaveQuery.getMessageContent())
                .speed(messageSaveQuery.getSpeed())
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .isReview(false)
                .build();
        messageMapper.insert(message);
    }

    /**
     * @Description 获取留言
     * @Author 23DAY
     * @Date 2023/1/28 19:34
     * @Param []
     * @Return java.util.List<site.day.blog.pojo.dto.MessageDTO>
     **/
    @Override
    public List<MessageDTO> getMessages() {
        List<Message> messageList = messageMapper.selectList(Wrappers.lambdaQuery(Message.class)
                .eq(Message::getIsReview, true)
                .orderByDesc(Message::getCreateTime));
        return mapStruct.MessageList2MessageDTOList(messageList);
    }

    @Override
    public List<MessageDTO> getBackMessages(Boolean isReview) {
        Page<Message> messagePage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Message> messageList = messageMapper.selectPage(messagePage, Wrappers.lambdaQuery(Message.class)
                .eq(Objects.nonNull(isReview), Message::getIsReview, isReview)
                .orderByDesc(Message::getCreateTime)).getRecords();
        PageUtil.setTotal(messagePage.getTotal());
        return mapStruct.MessageList2MessageDTOList(messageList);
    }

    @Override
    public void updateMessagesStatus(MessageStatusQuery query) {
        List<Message> messageList = query.getIdList().stream().map(id -> Message.builder()
                        .id(id)
                        .isReview(query.getIsReview())
                        .build())
                .collect(Collectors.toList());
        updateBatchById(messageList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMessageByIds(List<Integer> messageIdList) {
        removeBatchByIds(messageIdList);
    }
}
