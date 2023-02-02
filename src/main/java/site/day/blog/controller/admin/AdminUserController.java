package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.dto.*;
import site.day.blog.pojo.vo.*;
import site.day.blog.pojo.vo.query.*;
import site.day.blog.service.ResourceService;
import site.day.blog.service.RoleService;
import site.day.blog.service.UserAuthService;
import site.day.blog.service.UserInfoService;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description
 * @ClassName AdminUserController
 * @Author 23DAY
 * @Date 2023/1/29 11:26
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员-用户模块")
@RestController
@RequestMapping("/admin/")
public class AdminUserController {

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation("获取用户区域分布")
    @GetMapping("/users/area")
    public ResponseAPI<?> getUserAreas(
            @ApiParam(name = "type", value = "用户类型")
            @NotBlank
                    Integer type) {
        List<UserAreaDTO> userAreaDTOList = userAuthService.getUserArea(type);
        List<UserAreaVO> userAreaVOList = mapStruct.UserAreaDTOList2UserAreaVOList(userAreaDTOList);
        return ResponseAPI.success(userAreaVOList);
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/users/list")
    public ResponseAPI<?> getUsers(
            @ApiParam(name = "type", value = "登录类型")
                    Integer type,
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<UserDTO> userDTOList = userAuthService.getBackUser(type);
        List<UserBackVO> userBackVOList = mapStruct.UserDTOList2UserBackVOList(userDTOList);
        return ResponseAPI.success(PageResult.build(userBackVOList));
    }

    @ApiOperation("修改密码")
    @PostMapping("/users/password")
    public ResponseAPI<?> updateUserPassword(
            @ApiParam(name = "userPasswordQuery", value = "修改密码")
            @Valid
            @RequestBody
                    UserPasswordQuery userPasswordQuery) {
        userAuthService.updateUserPassword(userPasswordQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("修改用户角色")
    @PostMapping("/users/role")
    public ResponseAPI<?> updateUserRole(
            @ApiParam(name = "userRoleQuery", value = "修改用户角色")
            @Valid
            @RequestBody
                    UserRoleQuery userRoleQuery) {
        userInfoService.updateUserRole(userRoleQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("修改用户状态")
    @GetMapping("users/status")
    public ResponseAPI<?> updateUserStatus(
            @ApiParam(name = "userStatusQuery", value = "修改用户状态")
            @Valid
            @RequestBody
                    UserStatusQuery userStatusQuery) {
        userAuthService.updateUserStatus(userStatusQuery);
        return ResponseAPI.success();
    }


    @ApiOperation("获取全部资源")
    @GetMapping("/resources/list")
    public ResponseAPI<?> getResources() {
        List<ResourceDTO> resourceDTOList = resourceService.getResources();
        List<ResourceVO> resourceVOList = mapStruct.ResourceDTOList2ResourceVOList(resourceDTOList);
        return ResponseAPI.success(resourceVOList);
    }

    @ApiOperation("删除资源")
    @GetMapping("/resources/{id}/delete")
    public ResponseAPI<?> deleteResource(
            @ApiParam(name = "id", value = "资源id")
            @PathVariable
                    Integer id) {
        resourceService.deleteResourceById(id);
        return ResponseAPI.success();
    }

    @ApiOperation("添加资源")
    @PostMapping("/resources/save")
    public ResponseAPI<?> saveOrUpdateResource(
            @ApiParam(name = "resourceSaveQuery", value = "添加资源")
            @Valid
            @RequestBody
                    ResourceSaveQuery resourceSaveQuery) {
        resourceService.saveOrUpdateResource(resourceSaveQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("获取角色列表")
    @GetMapping("/roles/list")
    public ResponseAPI<?> getRoles() {
        List<RoleDTO> roleDTOList = roleService.listRoles();
        List<RoleVO> roleVOList = mapStruct.RoleDTOList2RoleVOList(roleDTOList);
        return ResponseAPI.success(roleVOList);
    }

    @ApiOperation("添加角色")
    @PostMapping("/roles/save")
    public ResponseAPI<?> saveOrUpdateRole(
            @ApiParam(name = "roleSaveQuery", value = "添加角色")
            @Valid
            @RequestBody
                    RoleSaveQuery roleSaveQuery) {
        roleService.saveOrUpdateRole(roleSaveQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("查看在线用户")
    @GetMapping("/users/online")
    public ResponseAPI<?> getOnlineUsers(
            @ApiParam(name = "pageQuery", value = "查询条件")
            @Valid
            @RequestParam(required = false)
                    PageQuery pageQuery) {
        List<UserDTO> userDTOList = userInfoService.getOnlineUsers();
        List<UserBackVO> userBackVOList = mapStruct.UserDTOList2UserBackVOList(userDTOList);
        return ResponseAPI.success(PageResult.build(userBackVOList));
    }


}
