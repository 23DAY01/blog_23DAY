package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Page;
import site.day.blog.mapper.PageMapper;
import site.day.blog.service.PageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 页面表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

}
