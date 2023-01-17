package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Tag;
import site.day.blog.mapper.TagMapper;
import site.day.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
