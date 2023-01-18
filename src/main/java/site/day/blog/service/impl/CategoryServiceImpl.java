package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Category;
import site.day.blog.mapper.CategoryMapper;
import site.day.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Category服务实现类
 * @ClassName CategoryServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
