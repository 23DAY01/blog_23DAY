package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.pojo.domain.FriendLink;
import site.day.blog.mapper.FriendLinkMapper;
import site.day.blog.pojo.dto.FriendLinkDTO;
import site.day.blog.pojo.vo.query.FriendLinkSaveQuery;
import site.day.blog.service.FriendLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;

import java.util.List;

/**
 * @Description FriendLink服务实现类
 * @ClassName FriendLinkServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Autowired
    private MapStruct mapStruct;

    @Override
    public List<FriendLinkDTO> getFriendLinks() {
        List<FriendLink> friendLinkList = friendLinkMapper.selectList(Wrappers.emptyWrapper());
        return mapStruct.FriendLinkList2FriendLinkDTOList(friendLinkList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateFriendLink(FriendLinkSaveQuery friendLinkSaveQuery) {
        FriendLink friendLink = mapStruct.FriendLinkSaveQuery2Friend(friendLinkSaveQuery);
        saveOrUpdate(friendLink);
    }

    @Override
    public void deleteFriendLink(List<Integer> linkIdList) {
        removeByIds(linkIdList);
    }
}
