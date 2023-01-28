package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.pojo.domain.Tag;
import site.day.blog.mapper.TagMapper;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;

import java.util.List;

/**
 * @Description Tag服务实现类
 * @ClassName TagServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private MapStruct mapStruct;


    @Override
    public List<TagDTO> getTags() {
        List<Tag> tagList = tagMapper.selectList(Wrappers.emptyWrapper());
        return mapStruct.tagList2tagDTOList(tagList);
    }
}
