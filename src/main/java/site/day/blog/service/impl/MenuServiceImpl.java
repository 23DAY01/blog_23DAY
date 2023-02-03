package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.xml.internal.ws.api.model.MEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.RoleMenuMapper;
import site.day.blog.pojo.domain.Menu;
import site.day.blog.mapper.MenuMapper;
import site.day.blog.pojo.domain.RoleMenu;
import site.day.blog.pojo.dto.MenuDTO;
import site.day.blog.pojo.vo.query.MenuSaveQuery;
import site.day.blog.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.service.RoleMenuService;
import site.day.blog.utils.MapStruct;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description Menu服务实现类
 * @ClassName MenuServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Cacheable(cacheNames = "menu", sync = true)
    @Override
    public List<MenuDTO> getMenus() {
        //查询所有菜单
        List<Menu> menuList = menuMapper.selectList(Wrappers.emptyWrapper());
        //获取顶级目录
        List<Menu> topMenuList = menuList.stream().filter(menu -> Objects.isNull(menu.getParentId()))
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList());
        //获取子菜单
        Map<Integer, List<Menu>> childMenuMap = menuList.stream()
                .filter(menu -> Objects.nonNull(menu.getParentId()))
                .collect(Collectors.groupingBy(Menu::getParentId));
        //封装成MenuDTO
        List<MenuDTO> menuDTOList = mapStruct.MenuList2MenuDTOList(topMenuList);
        for (MenuDTO menuDTO : menuDTOList) {
            List<Menu> childMenuList = childMenuMap.get(menuDTO.getId());
            List<MenuDTO> childMenuDTOList = mapStruct.MenuList2MenuDTOList(childMenuList).stream()
                    .sorted(Comparator.comparing(MenuDTO::getOrderNum)).collect(Collectors.toList());
            menuDTO.setChildMenuList(childMenuDTOList);
        }
        return menuDTOList;
    }

    @CachePut(cacheNames = "menu")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateMenu(MenuSaveQuery menuSaveQuery) {
        Menu menu = mapStruct.MenuSaveQuery2Menu(menuSaveQuery);
        saveOrUpdate(menu);
    }

    @CacheEvict(cacheNames = "menu",allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenuById(Integer id) {
        //菜单不可以与角色相关联
        Long relationshipCount = roleMenuMapper.selectCount(Wrappers.lambdaQuery(RoleMenu.class)
                .eq(RoleMenu::getMenuId, id));
        if (relationshipCount > 0) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.MENU_ROLE_RELATION);
        }
        //该菜单的子菜单一起删除
        List<Integer> menuIdList = menuMapper.selectList(Wrappers.lambdaQuery(Menu.class)
                .eq(Menu::getParentId, id)).stream().map(Menu::getId).collect(Collectors.toList());
        menuIdList.add(id);
        removeBatchByIds(menuIdList);
    }
}
