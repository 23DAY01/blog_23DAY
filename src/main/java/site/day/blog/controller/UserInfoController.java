package site.day.blog.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.pojo.vo.query.EmailQuery;
import site.day.blog.pojo.vo.query.UserInfoQuery;
import site.day.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;

/**
 * @Description UserInfo控制器
 * @ClassName UserInfoController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "userInfo模块")
@RestController
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询userInfo", notes = "根据id查询userInfo")
    @GetMapping("/userInfos/{id}")
    public ResponseAPI<?> getUserInfoById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(userInfoService.getById(id));
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/info")
    public ResponseAPI<?> updateUserInfo(
            @ApiParam(name = "userInfoQuery", value = "用户信息请求")
            @Valid
            @RequestBody
                    UserInfoQuery userInfoQuery) {
        userInfoService.updateUserInfo(userInfoQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("更新用户头像")
    @PostMapping("/avatar")
    public ResponseAPI<?> updateUserAvatar(
            @ApiParam(name = "file", value = "用户头像")
                    MultipartFile file) {
        String url = userInfoService.updateUserAvatar(file);
        return ResponseAPI.success(url);
    }

    @ApiOperation("绑定用户邮箱")
    @PostMapping("/email")
    public ResponseAPI<?> saveUserEmail(
            @ApiParam(name = "emailQuery", value = "邮箱")
            @Valid
            @RequestBody
                    EmailQuery emailQuery) {
        userInfoService.saveUserEmail(emailQuery);
        return ResponseAPI.success();
    }


}
