package site.day.blog.handler.securityHandler;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import site.day.blog.pojo.dto.RoleResourceDTO;
import site.day.blog.service.RoleResourceService;

import java.util.Collection;
import java.util.List;

/**
 * @Description 自定义接口拦截规则
 * @ClassName FilterInvocationSecurityMetadataSourceImpl
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    /**
     * 资源角色列表
     */
    private static List<RoleResourceDTO> roleResourceDTOs;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 加载资源角色信息
     */
//    @PostConstruct
    private void loadRoleReSource() {
        roleResourceDTOs =roleResourceService.listRoleResourceDTOs();
    }

    /**
     * 清空接口角色信息
     */
    public void clearDataSource() {
        roleResourceDTOs = null;
    }

    // 该方法的参数是受保护对象，在基于 URL 地址的权限控制中，受保护对象就是 FilterInvocation；
    // 该方法的返回值则是访问受保护对象所需要的权限。在该方法里边，我们首先从受保护对象 FilterInvocation
    // 中提取出当前请求的 URL 地址，例如 /admin/hello，然后通过 menuService 对象查询出所有的菜单
    // 数据（每条数据中都包含访问该条记录所需要的权限），遍历查询出来的菜单数据，如果当前请求的 URL 地址和
    // 菜单中某一条记录的 pattern 属性匹配上了（例如 /admin/hello 匹配上 /admin/**），那么我们就可以
    // 获取当前请求所需要的权限。从 menu 对象中获取 roles 属性，并将其转为一个数组，然后
    // 通过 SecurityConfig.createList 方法创建一个 Collection<ConfigAttribute> 对象并返回。
    // 如果当前请求的 URL 地址和数据库中 menu 表的所有项都匹配不上，那么最终返回 null。如果返回 null，
    // 那么受保护对象到底能不能访问呢？这就要看 AbstractSecurityInterceptor 对象中的
    // rejectPublicInvocations 属性了，该属性默认为 false，表示当 getAttributes 方法返回 null 时，
    // 允许访问受保护对象
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 修改接口角色关系后重新加载
        if (CollectionUtils.isEmpty(roleResourceDTOs)) {
            this.loadRoleReSource();
        }
        FilterInvocation fi = (FilterInvocation) object;
        // 获取用户请求方式
        String method = fi.getRequest().getMethod();
        // 获取用户请求Url
        String url = fi.getRequest().getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 获取接口角色信息，若为匿名接口则放行，若无对应角色则禁止
        for (RoleResourceDTO roleResourceDTO : roleResourceDTOs) {
            if (antPathMatcher.match(roleResourceDTO.getUrl(), url) && roleResourceDTO.getRequestMethod().equals(method)) {
                List<String> roleList = roleResourceDTO.getRoleList();
                if (CollectionUtils.isEmpty(roleList)) {
                    return SecurityConfig.createList("disable");
                }
                return SecurityConfig.createList(roleList.toArray(new String[]{}));
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
