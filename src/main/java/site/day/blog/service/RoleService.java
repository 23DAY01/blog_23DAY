package site.day.blog.service;

import site.day.blog.pojo.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.RoleDTO;
import site.day.blog.pojo.vo.query.RoleSaveQuery;

import java.util.List;

/**
 * @Description Role服务类
 * @ClassName RoleService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface RoleService extends IService<Role> {

    public List<RoleDTO> listRolesByUserInfoId(Integer userInfoId);

    List<RoleDTO> listRoles();

    void saveOrUpdateRole(RoleSaveQuery roleSaveQuery);
}
