package site.day.blog.service;

import site.day.blog.pojo.domain.Talk;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.TalkDTO;

import java.util.List;

/**
 * @Description Talk服务类
 * @ClassName TalkService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface TalkService extends IService<Talk> {

    List<TalkDTO> getTalks();

    List<String> getHomeTalkContent();

    TalkDTO getTalkById(Integer id);

    void saveTalkLike(Integer id);

}
