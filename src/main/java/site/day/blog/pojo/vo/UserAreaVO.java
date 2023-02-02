package site.day.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 用户地域
 * @ClassName UserAreaVO
 * @Author 23DAY
 * @Date 2023/2/2 0:32
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserAreaVO", description = "用户地域")
public class UserAreaVO {

    /**
     * 地域名称
     */
    private String areaName;

    /**
     * 数量
     */
    private Integer count;

}
