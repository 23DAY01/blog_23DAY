package site.day.blog.service;

import site.day.blog.pojo.domain.FriendLink;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.FriendLinkDTO;
import site.day.blog.pojo.vo.query.FriendLinkSaveQuery;

import java.util.List;

/**
 * @Description FriendLink服务类
 * @ClassName FriendLinkService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLinkDTO> getFriendLinks();

    void saveOrUpdateFriendLink(FriendLinkSaveQuery friendLinkSaveQuery);

    void deleteFriendLink(List<Integer> linkIdList);
}
