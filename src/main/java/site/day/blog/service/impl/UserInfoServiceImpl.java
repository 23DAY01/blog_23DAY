package site.day.blog.service.impl;

import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.mapper.UserInfoMapper;
import site.day.blog.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description UserInfo服务实现类
 * @ClassName UserInfoServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
