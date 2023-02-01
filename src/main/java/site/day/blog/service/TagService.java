package site.day.blog.service;

import site.day.blog.pojo.domain.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.pojo.vo.query.TagSaveQuery;

import java.util.List;

/**
 * @Description Tag服务类
 * @ClassName TagService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface TagService extends IService<Tag> {

    List<TagDTO> getTags();

    List<TagDTO> getBackTags();

    void saveOrUpdateTag(TagSaveQuery tagSaveQuery);

    void deleteTagByIds(List<Integer> idList);
}
