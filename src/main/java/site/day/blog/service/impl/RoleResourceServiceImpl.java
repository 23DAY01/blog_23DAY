package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.pojo.domain.Resource;
import site.day.blog.pojo.domain.Role;
import site.day.blog.pojo.domain.RoleResource;
import site.day.blog.mapper.RoleResourceMapper;
import site.day.blog.pojo.dto.RoleResourceDTO;
import site.day.blog.service.ResourceService;
import site.day.blog.service.RoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.service.RoleService;
import site.day.blog.utils.MapStruct;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

/**
 * @Description RoleResource服务实现类
 * @ClassName RoleResourceServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MapStruct mapStruct;


    /**
     * @Description 获取所有不可匿名访问的资源与其相关联的角色名的mapList（RoleResourceDTO）
     * @Author 23DAY
     * @Date 2022/9/17 17:51
     * @Param []
     * @return java.util.List<site.day.template.pojo.dto.RoleResourceDTO>
     **/
    @Override
    public List<RoleResourceDTO> listRoleResourceDTOs() {
//        获取不能匿名访问的resource
        List<Resource> resourceList = resourceService.listResourceNotAnonymous();
//        转换成RoleResourceDTO
        List<RoleResourceDTO> roleResourceDTOList = mapStruct.resources2roleResources(resourceList);
//        获取不能匿名访问的resourceId
        Set<Integer> resourceIds = resourceList.stream().map(Resource::getId).collect(Collectors.toSet());
//        查找不能匿名访问的资源对应的角色
        LambdaQueryWrapper<RoleResource> roleResourceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleResourceLambdaQueryWrapper.in(CollectionUtils.isNotEmpty(resourceIds),RoleResource::getResourceId, resourceIds);
        List<RoleResource> roleResourceList = list(roleResourceLambdaQueryWrapper);
//        获取角色id
        Set<Integer> roleIds = roleResourceList.stream().map(RoleResource::getRoleId).collect(Collectors.toSet());
//        获取角色
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.in(CollectionUtils.isNotEmpty(roleIds),Role::getId,roleIds);
        List<Role> roleList = roleService.list(roleLambdaQueryWrapper);
//        转换

//        以资源id：角色id做一个map
        Map<Integer, List<Integer>> resourceId2roleIdMap = roleResourceList.stream().collect(Collectors.groupingBy(RoleResource::getResourceId, Collectors.mapping(RoleResource::getRoleId, Collectors.toList())));

//      最终形成资源id:角色名的mapList
        for (RoleResourceDTO roleResourceDTO : roleResourceDTOList) {
//            获取当前资源的所有角色
            List<Role> roles = ListUtils.select(roleList, role -> emptyIfNull(resourceId2roleIdMap.get(roleResourceDTO.getResourceId())).contains(role.getId()));
//            填充
            roleResourceDTO.setRoleList(roles.stream().map(Role::getRoleLabel).collect(Collectors.toList()));
        }

        return roleResourceDTOList;
    }

}
