package site.day.blog.controller;

import org.springframework.web.bind.annotation.*;
import site.day.blog.annotation.AccessLimit;
import site.day.blog.pojo.vo.query.UserAuthQuery;
import site.day.blog.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * @Description UserAuth控制器
 * @ClassName UserAuthController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "userAuth模块")
@RestController
@RequestMapping("/users")
public class UserAuthController {

    @Autowired
    public UserAuthService userAuthService;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询userAuth", notes = "根据id查询userAuth")
    @GetMapping("/userAuths/{id}")
    public ResponseAPI<?> getUserAuthById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(userAuthService.getById(id));
    }

    /**
     * @Description 发送邮箱验证码
     * @Author 23DAY
     * @Date 2023/1/29 0:16
     * @Param [java.lang.String]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @AccessLimit(seconds = 60, maxCount = 2)
    @ApiOperation("发送邮箱验证码")
    @GetMapping("/verify")
    public ResponseAPI<?> sendVerifyCode(
            @ApiParam(name = "email", value = "邮箱")
            @Email(message = "邮箱格式错误")
                    String email) {
        userAuthService.sendEmailCode(email);
        return ResponseAPI.success();
    }

    /**
     * @Description 用户注册
     * @Author 23DAY
     * @Date 2023/1/29 9:39
     * @Param [site.day.blog.pojo.vo.query.UserAuthQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseAPI<?> register(
            @ApiParam(name = "userAuthQuery", value = "用户认证请求")
            @Valid
            @RequestBody
                    UserAuthQuery userAuthQuery) {
        userAuthService.register(userAuthQuery);
        return ResponseAPI.success();
    }

    @ApiOperation("用户修改密码")
    @PostMapping("/password")
    public ResponseAPI<?> updatePassword(
            @ApiParam(name = "userAuthQuery", value = "用户认证请求")
            @Valid
            @RequestBody
                    UserAuthQuery userAuthQuery){
        userAuthService.updatePassword(userAuthQuery);
        return ResponseAPI.success();
    }



}
