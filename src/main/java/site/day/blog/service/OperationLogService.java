package site.day.blog.service;

import site.day.blog.pojo.domain.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.OperationLogDTO;

import java.util.List;

/**
 * @Description OperationLog服务类
 * @ClassName OperationLogService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface OperationLogService extends IService<OperationLog> {

    List<OperationLogDTO> getOperationLogs();

    void deleteOperationLog(List<Integer> logIdList);
}
