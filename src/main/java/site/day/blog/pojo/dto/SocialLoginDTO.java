package site.day.blog.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description
 * @ClassName SocialLoginTokenDTO
 * @Author 23DAY
 * @Date 2023/2/2 14:10
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SocialLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * openId
     */
    private String openId;


    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 登录类型
     */
    private Integer loginType;

}
