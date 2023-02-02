package site.day.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description
 * @ClassName QQTokenDTO
 * @Author 23DAY
 * @Date 2023/2/2 15:37
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class QQTokenDTO {

    /**
     * openId
     */
    private String openId;

    /**
     * 客户端id
     */
    private String client_id;

}
