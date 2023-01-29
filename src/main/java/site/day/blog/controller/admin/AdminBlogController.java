package site.day.blog.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.day.blog.pojo.dto.BlogInfoDTO;
import site.day.blog.pojo.vo.BlogBackInfoVO;
import site.day.blog.service.BlogService;
import site.day.blog.service.impl.BlogServiceImpl;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName AdminBlogController
 * @Author 23DAY
 * @Date 2023/1/29 11:26
 * @Version 1.0
 */
@Slf4j
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admin/blog")
public class AdminBlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MapStruct mapStruct;

    @ApiOperation("查看后台博客信息")
    @GetMapping("/info")
    public ResponseAPI<?> getBlogBackInfo() {
        BlogInfoDTO blogInfo = blogService.getBlogInfo();
        BlogBackInfoVO blogBackInfoVO = mapStruct.BlogInfoDTO2BlogBackInfoVO(blogInfo);
        return ResponseAPI.success(blogBackInfoVO);
    }



}
