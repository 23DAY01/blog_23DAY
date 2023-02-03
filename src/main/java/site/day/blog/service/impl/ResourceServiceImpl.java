package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.handler.securityHandler.FilterInvocationSecurityMetadataSourceImpl;
import site.day.blog.mapper.RoleResourceMapper;
import site.day.blog.pojo.domain.Menu;
import site.day.blog.pojo.domain.Resource;
import site.day.blog.mapper.ResourceMapper;
import site.day.blog.pojo.domain.RoleResource;
import site.day.blog.pojo.dto.MenuDTO;
import site.day.blog.pojo.dto.ResourceDTO;
import site.day.blog.pojo.vo.query.ResourceSaveQuery;
import site.day.blog.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description Resource服务实现类
 * @ClassName ResourceServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Lazy
    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    @Override
    public List<Resource> listResourceNotAnonymous() {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(Resource::getParentId)
                .eq(Resource::getIsAnonymous, false);
        return list(wrapper);
    }

    @Cacheable(cacheNames = "resource", sync = true)
    @Override
    public List<ResourceDTO> getResources() {
        List<Resource> resourceList = resourceMapper.selectList(Wrappers.lambdaQuery(Resource.class)
                .orderByDesc(Resource::getCreateTime));
        //获取顶级资源
        List<Resource> topResourceList = resourceList.stream()
                .filter(resource -> Objects.isNull(resource.getParentId()))
                .collect(Collectors.toList());
        //获取子资源
        Map<Integer, List<Resource>> childResourceMap = resourceList.stream()
                .filter(resource -> Objects.nonNull(resource.getParentId()))
                .collect(Collectors.groupingBy(Resource::getParentId));
        //封装
        List<ResourceDTO> resourceDTOList = mapStruct.ResourceList2ResourceDTOList(topResourceList);
        for (ResourceDTO resourceDTO : resourceDTOList) {
            List<Resource> childResourceList = childResourceMap.get(resourceDTO.getParentId());
            List<ResourceDTO> childResourceDTOList = mapStruct.ResourceList2ResourceDTOList(childResourceList)
                    .stream().sorted(Comparator.comparing(ResourceDTO::getCreateTime))
                    .collect(Collectors.toList());
            resourceDTO.setChildResourceList(childResourceDTOList);
        }
        return resourceDTOList;

    }

    @CacheEvict(cacheNames = "resource", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteResourceById(Integer id) {
        Long count = roleResourceMapper.selectCount(Wrappers.lambdaQuery(RoleResource.class)
                .eq(RoleResource::getResourceId, id));
        if (count > 0) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.RESOURCE_ROLE_RELATION);
        }
        List<Integer> resourceIdList = resourceMapper.selectList(Wrappers.lambdaQuery(Resource.class)
                        .eq(Resource::getParentId, id))
                .stream()
                .map(Resource::getId)
                .collect(Collectors.toList());
        resourceIdList.add(id);
        resourceMapper.deleteBatchIds(resourceIdList);
    }

    @CachePut(cacheNames = "resource")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateResource(ResourceSaveQuery resourceSaveQuery) {
        Resource resource = mapStruct.ResourceSaveQuery2Resource(resourceSaveQuery);
        saveOrUpdate(resource);
        filterInvocationSecurityMetadataSource.clearDataSource();
    }

}
