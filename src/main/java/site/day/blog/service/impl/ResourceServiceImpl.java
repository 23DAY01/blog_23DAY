package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import site.day.blog.pojo.domain.Resource;
import site.day.blog.mapper.ResourceMapper;
import site.day.blog.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description Resource服务实现类
 * @ClassName ResourceServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<Resource> listResourceNotAnonymous() {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(Resource::getParentId)
                .eq(Resource::getIsAnonymous,false);
        return list(wrapper);
    }

}
