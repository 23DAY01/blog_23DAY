package site.day.blog.service;

import site.day.blog.pojo.domain.RoleResource;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.RoleResourceDTO;

import java.util.List;

/**
 * @Description RoleResource服务类
 * @ClassName RoleResourceService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface RoleResourceService extends IService<RoleResource> {

    public List<RoleResourceDTO> listRoleResourceDTOs();

}
