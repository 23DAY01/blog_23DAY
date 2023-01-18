package site.day.blog.service.impl;

import site.day.blog.pojo.domain.UserRole;
import site.day.blog.mapper.UserRoleMapper;
import site.day.blog.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description UserRole服务实现类
 * @ClassName UserRoleServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
