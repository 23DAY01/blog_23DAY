package site.day.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import site.day.blog.pojo.dto.FriendLinkDTO;
import site.day.blog.pojo.vo.FriendLinkHomeVO;
import site.day.blog.service.FriendLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import java.util.List;

/**
 * @Description FriendLink控制器
 * @ClassName FriendLinkController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "friendLink模块")
@RestController
@RequestMapping("/links")
public class FriendLinkController {

    @Autowired
    public FriendLinkService friendLinkService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询friendLink", notes = "根据id查询friendLink")
    @GetMapping("/friendLinks/{id}")
    public ResponseAPI<?> getFriendLinkById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(friendLinkService.getById(id));
    }

    /**
     * @Description 查看友链
     * @Author 23DAY
     * @Date 2023/1/28 18:55
     * @Param []
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("查看友链")
    @GetMapping("")
    public ResponseAPI<?> getFriendLinks() {
        List<FriendLinkDTO> friendLinkDTOList = friendLinkService.getFriendLinks();
        List<FriendLinkHomeVO> friendLinkHomeVOList = mapStruct.FriendLinkDTOList2FriendLinkHomeVOList(friendLinkDTOList);
        return ResponseAPI.success(friendLinkHomeVOList);
    }

}
