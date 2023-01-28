package site.day.blog.controller;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;
import site.day.blog.pojo.dto.MessageDTO;
import site.day.blog.pojo.vo.query.MessageQuery;
import site.day.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.ResponseAPI;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description Message控制器
 * @ClassName MessageController
 * @Author 23DAY
 * @Date 2023/01/18 20:48
 * @Version 1.0
 */
@Slf4j
@Api(tags = "message模块")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MapStruct mapStruct;

    /**
     * @Description 根据id查询
     * @Author 23DAY
     * @Date 2023/01/18 20:48
     * @Param [java.lang.Integer]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation(value = "根据id查询message", notes = "根据id查询message")
    @GetMapping("/messages/{id}")
    public ResponseAPI<?> getMessageById(
            @ApiParam(name = "id", value = "主键", required = true)
            @PathVariable("id")
                    Integer id) {
        return ResponseAPI.success(messageService.getById(id));
    }

    /**
     * @Description 添加留言
     * @Author 23DAY
     * @Date 2023/1/28 19:25
     * @Param [site.day.blog.pojo.vo.query.MessageQuery]
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("添加留言")
    @PostMapping("")
    public ResponseAPI<?> saveMessage(
            @ApiParam(name = "messageQuery", value = "留言", required = true)
            @Valid
            @RequestBody
                    MessageQuery messageQuery) {
        messageService.saveMessage(messageQuery);
        return ResponseAPI.success();
    }

    /**
     * @Description 获取留言
     * @Author 23DAY
     * @Date 2023/1/28 19:35
     * @Param []
     * @Return site.day.blog.utils.ResponseAPI<?>
     **/
    @ApiOperation("获取留言")
    @GetMapping("/list")
    public ResponseAPI<?> getMessages() {
        List<MessageDTO> messageDTOList = messageService.getMessages();
        return ResponseAPI.success();
    }

}
