package site.day.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 用户类型枚举
 * @ClassName UserAreaTypeEnum
 * @Author 23DAY
 * @Date 2022/9/15 20:34
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum UserAreaTypeEnum {
    /**
     * 用户
     */
    USER(1, "用户"),
    /**
     * 游客
     */
    VISITOR(2, "游客");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String desc;

    /**
     * @Description 获取用户区域类型
     * @Author 23DAY
     * @Date 2022/11/2 20:26
     * @Param [java.lang.Integer]
     * @return site.day.template.enums.UserAreaTypeEnum
     **/
    public static UserAreaTypeEnum getUserAreaType(Integer type) {
        for (UserAreaTypeEnum value : UserAreaTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

}
