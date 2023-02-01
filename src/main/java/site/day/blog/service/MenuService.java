package site.day.blog.service;

import site.day.blog.pojo.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.MenuDTO;
import site.day.blog.pojo.vo.query.MenuSaveQuery;

import java.util.List;

/**
 * @Description Menu服务类
 * @ClassName MenuService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface MenuService extends IService<Menu> {

    List<MenuDTO> getMenus();

    void saveOrUpdateMenu(MenuSaveQuery menuSaveQuery);

    void deleteMenuById(Integer id);
}
