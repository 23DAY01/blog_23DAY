package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.pojo.domain.Talk;
import site.day.blog.mapper.TalkMapper;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.pojo.dto.TalkDTO;
import site.day.blog.service.TalkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static site.day.blog.constant.RedisConst.TALK_LIKE_COUNT;
import static site.day.blog.constant.RedisConst.TALK_USER_LIKE;

/**
 * @Description Talk服务实现类
 * @ClassName TalkServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {

    @Autowired
    private TalkMapper talkMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * @Description 获取首页说说
     * @Author 23DAY
     * @Date 2023/1/28 20:42
     * @Param []
     * @Return java.util.List<site.day.blog.pojo.dto.TalkDTO>
     **/
    @Override
    public List<TalkDTO> getTalks() {
        Page<Talk> talkPage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Talk> talkList = talkMapper.selectPage(talkPage, Wrappers.lambdaQuery(Talk.class)
                .orderByDesc(Talk::getIsTop)
                .orderByDesc(Talk::getCreateTime)
                .eq(Talk::getStatus, true)).getRecords();
        PageUtil.setTotal(talkPage.getTotal());
        List<TalkDTO> talkDTOList = mapStruct.TalkList2TalkDTOList(talkList);
        //查询点赞、用户信息并封装
        Map<String, Object> talkLikeCountMap = (Map<String, Object>) (Object) redisUtil.hGetAll(TALK_LIKE_COUNT);
        List<Integer> userInfoIdList = talkDTOList.stream().map(TalkDTO::getUserId).collect(Collectors.toList());
        Map<Integer, UserInfo> id2UserinfoMap = userInfoMapper.selectBatchIds(userInfoIdList)
                .stream().collect(Collectors.toMap(UserInfo::getId, userInfo -> userInfo));
        for (TalkDTO talkDTO : talkDTOList) {
            talkDTO.setLikeCount((Integer) talkLikeCountMap.get(talkDTO.getId().toString()));
            talkDTO.setAvatar(id2UserinfoMap.get(talkDTO.getUserId()).getAvatar());
            talkDTO.setNickName(id2UserinfoMap.get(talkDTO.getUserId()).getNickname());
        }
        return talkDTOList;
    }

    /**
     * @Description 获取首页说说内容
     * @Author 23DAY
     * @Date 2023/1/28 20:42
     * @Param []
     * @Return java.util.List<java.lang.String>
     **/
    @Override
    public List<String> getHomeTalkContent() {
        return talkMapper.selectList(Wrappers.lambdaQuery(Talk.class)
                        .eq(Talk::getStatus, true)
                        .orderByDesc(Talk::getIsTop)
                        .orderByDesc(Talk::getCreateTime)
                        .last("limit 10"))
                .stream().map(Talk::getContent)
                .collect(Collectors.toList());
    }

    /**
     * @Description id获取说说
     * @Author 23DAY
     * @Date 2023/1/28 21:19
     * @Param [java.lang.Integer]
     * @Return site.day.blog.pojo.dto.TalkDTO
     **/
    @Override
    public TalkDTO getTalkById(Integer id) {
        Talk talk = talkMapper.selectOne(Wrappers.lambdaQuery(Talk.class).eq(Talk::getStatus, true));
        if (Objects.isNull(talk)) throw BusinessException.withErrorCodeEnum(StatusCodeEnum.TALK_MISSING);
        TalkDTO talkDTO = mapStruct.Talk2TalkDTO(talk);
        talkDTO.setLikeCount((Integer) redisUtil.hGet(TALK_LIKE_COUNT, id.toString()));
        UserInfo userinfo = userInfoMapper.selectById(talkDTO.getUserId());
        talkDTO.setAvatar(userinfo.getAvatar());
        talkDTO.setNickName(userinfo.getNickname());
        return talkDTO;
    }

    @Override
    public void saveTalkLike(Integer id) {
        String talkUserLikeKey = TALK_USER_LIKE + AuthUtil.getLoginUser().getUserInfo().getId();
        //在set中获取用户是否对该说说点赞
        if (redisUtil.sIsMember(talkUserLikeKey, id)) {
            redisUtil.sRemove(talkUserLikeKey, id);
            redisUtil.hDecr(TALK_LIKE_COUNT, id.toString(), 1);
        } else {
            redisUtil.sAdd(talkUserLikeKey, id);
            redisUtil.hIncr(TALK_LIKE_COUNT, id.toString(), 1);
        }
    }
}
