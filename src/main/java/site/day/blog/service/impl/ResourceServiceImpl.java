package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Resource;
import site.day.blog.mapper.ResourceMapper;
import site.day.blog.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Resource服务实现类
 * @ClassName ResourceServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
