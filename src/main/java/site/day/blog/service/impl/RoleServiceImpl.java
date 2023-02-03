package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.mapper.RoleMenuMapper;
import site.day.blog.mapper.RoleResourceMapper;
import site.day.blog.mapper.UserRoleMapper;
import site.day.blog.pojo.domain.Role;
import site.day.blog.mapper.RoleMapper;
import site.day.blog.pojo.domain.RoleMenu;
import site.day.blog.pojo.domain.RoleResource;
import site.day.blog.pojo.domain.UserRole;
import site.day.blog.pojo.dto.RoleDTO;
import site.day.blog.pojo.vo.query.RoleSaveQuery;
import site.day.blog.service.RoleResourceService;
import site.day.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description Role服务实现类
 * @ClassName RoleServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private MapStruct mapStruct;

    @Override
    public List<RoleDTO> listRolesByUserInfoId(Integer userInfoId) {
//        根据userInfoId查询userRole 提取roleId
        Set<Integer> roleIds = userRoleMapper.selectList(Wrappers.lambdaQuery(UserRole.class)
                        .eq(UserRole::getUserId, userInfoId))
                .stream().map(UserRole::getRoleId).collect(Collectors.toSet());

//        根据roleId查询role 提取roleLabel
        List<Role> roleList = list(Wrappers.lambdaQuery(Role.class)
                .in(CollectionUtils.isNotEmpty(roleIds), Role::getId, roleIds));
        return mapStruct.RoleList2RoleDTOList(roleList);
    }

    @Cacheable(cacheNames = "role",sync = true)
    @Override
    public List<RoleDTO> listRoles() {
        List<Role> roleList = roleMapper.selectList(Wrappers.emptyWrapper());
        List<RoleDTO> roleDTOList = mapStruct.RoleList2RoleDTOList(roleList);
        //查询资源关系
        Map<Integer, List<Integer>> roleId2ResourceIdMap = roleResourceMapper.selectList(Wrappers.emptyWrapper()).stream()
                .collect(Collectors.groupingBy(RoleResource::getRoleId, Collectors.mapping(RoleResource::getResourceId, Collectors.toList())));
        //查询菜单关系
        Map<Integer, List<Integer>> roleId2MenuIdMap = roleMenuMapper.selectList(Wrappers.emptyWrapper()).stream()
                .collect(Collectors.groupingBy(RoleMenu::getRoleId, Collectors.mapping(RoleMenu::getMenuId, Collectors.toList())));
        for (RoleDTO roleDTO : roleDTOList) {
            roleDTO.setResourceIdList(ListUtils.emptyIfNull(roleId2ResourceIdMap.get(roleDTO.getId())));
            roleDTO.setMenuIdList(ListUtils.emptyIfNull(roleId2MenuIdMap.get(roleDTO.getId())));
        }
        return roleDTOList;
    }

    @CachePut(cacheNames = "role")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateRole(RoleSaveQuery roleSaveQuery) {

    }

}
