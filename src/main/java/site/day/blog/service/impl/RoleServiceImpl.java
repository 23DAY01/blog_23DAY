package site.day.blog.service.impl;

import site.day.blog.pojo.domain.Role;
import site.day.blog.mapper.RoleMapper;
import site.day.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description Role服务实现类
 * @ClassName RoleServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
