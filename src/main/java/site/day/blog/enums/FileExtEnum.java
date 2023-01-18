package site.day.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 文件拓展名枚举
 * @ClassName FileExtEnum
 * @Author 23DAY
 * @Date 2022/9/15 20:34
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum FileExtEnum {
    /**
     * jpg文件
     */
    JPG(".jpg", "jpg文件"),
    /**
     * png文件
     */
    PNG(".png", "png文件"),
    /**
     * Jpeg文件
     */
    JPEG(".jpeg", "jpeg文件"),
    /**
     * wav文件
     */
    WAV(".wav", "wav文件"),
    /**
     * md文件
     */
    MD(".md","markdown文件"),
    /**
     * txt文件
     */
    TXT(".txt","txt文件");

    /**
     * @Description 获取文件格式
     * @Author 23DAY
     * @Date 2022/11/2 20:24
     * @Param [java.lang.String]
     * @return site.day.template.enums.FileExtEnum
     **/
    public static FileExtEnum getFileExt(String extName) {
        for (FileExtEnum value : FileExtEnum.values()) {
            if (value.getExtName().equalsIgnoreCase(extName)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 扩展名
     */
    private final String extName;

    /**
     * 描述
     */
    private final String desc;

}
