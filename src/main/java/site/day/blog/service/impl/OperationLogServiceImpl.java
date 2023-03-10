package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.pojo.domain.OperationLog;
import site.day.blog.mapper.OperationLogMapper;
import site.day.blog.pojo.dto.OperationLogDTO;
import site.day.blog.service.OperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @Description OperationLog服务实现类
 * @ClassName OperationLogServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 获取操作日志
     * @Author 23DAY
     * @Date 2023/2/3 10:21
     * @Param []
     * @Return java.util.List<site.day.blog.pojo.dto.OperationLogDTO>
     **/
    @Cacheable(cacheNames = "operationLog", sync = true)
    @Override
    public List<OperationLogDTO> getOperationLogs() {
        Page<OperationLog> operationLogPage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<OperationLog> operationLogList = operationLogMapper.selectPage(operationLogPage, Wrappers.lambdaQuery(OperationLog.class)
                .orderByDesc(OperationLog::getCreateTime)).getRecords();
        PageUtil.setTotal(operationLogPage.getTotal());
        return mapStruct.OperationLogList2OperationLogDTOList(operationLogList);
    }

    /**
     * @Description 删除操作日志
     * @Author 23DAY
     * @Date 2023/2/3 10:29
     * @Param [java.util.List<java.lang.Integer>]
     * @Return void
     **/
    @CacheEvict(cacheNames = "operationLog", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteOperationLog(List<Integer> logIdList) {
        removeByIds(logIdList);
    }
}
