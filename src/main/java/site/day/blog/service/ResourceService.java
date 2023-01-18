package site.day.blog.service;

import site.day.blog.pojo.domain.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description Resource服务类
 * @ClassName ResourceService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface ResourceService extends IService<Resource> {

    public List<Resource> listResourceNotAnonymous();

}
