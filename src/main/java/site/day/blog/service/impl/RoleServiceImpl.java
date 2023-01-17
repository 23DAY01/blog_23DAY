package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Role;
import site.day.blog.mapper.RoleMapper;
import site.day.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
