package site.day.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 用户地域
 * @ClassName UserAreaDTO
 * @Author 23DAY
 * @Date 2023/2/1 22:43
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UserAreaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地域名称
     */
    private String areaName;

    /**
     * 数量
     */
    private Integer count;

}
