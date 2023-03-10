package site.day.blog.service;

import site.day.blog.pojo.domain.View;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.ViewDTO;

import java.util.List;

/**
 * @Description View服务类
 * @ClassName ViewService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface ViewService extends IService<View> {

    public List<ViewDTO> getViewsDuring(Integer ago);

}
