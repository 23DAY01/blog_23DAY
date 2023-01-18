package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Tag;
import site.day.blog.mapper.TagMapper;
import site.day.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Tag服务实现类
 * @ClassName TagServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
