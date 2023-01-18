package site.day.blog.aaTest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.day.blog.annotation.AccessLimit;
import site.day.blog.annotation.OptLog;
import site.day.blog.constant.OptTypeConst;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.pojo.domain.UserInfo;
import site.day.blog.service.UserInfoService;
import site.day.blog.utils.ResponseAPI;

import java.util.List;

/**
 * @Description
 * @ClassName helloController
 * @Author 23DAY
 * @Date 2023/1/19 0:18
 * @Version 1.0
 */
@RestController
public class helloController {


    @Autowired
    private UserInfoService userInfoService;


    @Cacheable(value = "hello")
    public List<UserInfo> getUserInfo() {
        return userInfoService.list();
    }


    @GetMapping("/hello")
    @ApiOperation(value = "hello")
    public String hello() {
        return "hello";
    }

    //    @RepeatSubmit
    @GetMapping("/hello2")
    @OptLog(optType = OptTypeConst.UPDATE)
    @AccessLimit(seconds = 10, maxCount = 2)
    @ApiOperation(value = "hello2")
    public ResponseAPI<?> hello2() {
        return ResponseAPI.success(getUserInfo());
    }


    @GetMapping("/hello3")
    public ResponseAPI<?> hello3() {
        throw BusinessException.withErrorCodeEnum(StatusCodeEnum.AUTH_FAIL);
//        return ResponseAPI.success("hello");
    }

}
