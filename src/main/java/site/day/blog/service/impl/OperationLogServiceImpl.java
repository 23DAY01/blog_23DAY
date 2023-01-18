package site.day.blog.service.impl;

import site.day.blog.pojo.domain.OperationLog;
import site.day.blog.mapper.OperationLogMapper;
import site.day.blog.service.OperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Description OperationLog服务实现类
 * @ClassName OperationLogServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

}
