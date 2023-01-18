package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.mapper.UserRoleMapper;
import site.day.blog.pojo.domain.Role;
import site.day.blog.mapper.RoleMapper;
import site.day.blog.pojo.domain.UserRole;
import site.day.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<String> listRolesByUserInfoId(Integer userInfoId) {

//        根据userInfoId查询userRole 提取roleId
        LambdaQueryWrapper<UserRole> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(UserRole::getUserId, userInfoId);
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper1);
        Set<Integer> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());

//        根据roleId查询role 提取roleLabel
        LambdaQueryWrapper<Role> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.in(Role::getId,roleIds);
        List<Role> roleList = list(wrapper2);
        return roleList.stream().map(Role::getRoleLabel).collect(Collectors.toList());

    }

}
