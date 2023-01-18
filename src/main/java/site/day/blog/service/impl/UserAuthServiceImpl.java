package site.day.blog.service.impl;

import site.day.blog.pojo.domain.UserAuth;
import site.day.blog.mapper.UserAuthMapper;
import site.day.blog.service.UserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description UserAuth服务实现类
 * @ClassName UserAuthServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

}
