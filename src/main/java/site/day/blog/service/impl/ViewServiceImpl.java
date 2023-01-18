package site.day.blog.service.impl;

import site.day.blog.pojo.domain.View;
import site.day.blog.mapper.ViewMapper;
import site.day.blog.service.ViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description View服务实现类
 * @ClassName ViewServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {

}
