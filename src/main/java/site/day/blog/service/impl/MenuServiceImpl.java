package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Menu;
import site.day.blog.mapper.MenuMapper;
import site.day.blog.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
