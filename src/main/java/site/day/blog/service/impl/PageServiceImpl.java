package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Page;
import site.day.blog.mapper.PageMapper;
import site.day.blog.service.PageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Page服务实现类
 * @ClassName PageServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

}
