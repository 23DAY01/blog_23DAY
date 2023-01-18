package site.day.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 上传模式类型枚举
 * @ClassName UploadModeEnum
 * @Author 23DAY
 * @Date 2022/9/15 20:34
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum UploadModeEnum {
    /**
     * oss
     */
    OSS("oss", "ossUploadStrategyImpl"),
    /**
     * 本地
     */
    LOCAL("local", "localUploadStrategyImpl"),

    /**
     * cos
     */
    COS("cos", "cosUploadStrategyImpl");

    /**
     * 模式
     */
    private final String mode;

    /**
     * 策略
     */
    private final String strategy;

    /**
     * @Description 获取策略
     * @Author 23DAY
     * @Date 2022/11/2 20:26
     * @Param [java.lang.String]
     * @return java.lang.String
     **/
    public static String getStrategy(String mode) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }

}
