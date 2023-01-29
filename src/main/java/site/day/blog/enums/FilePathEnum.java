package site.day.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 文件路径枚举
 * @ClassName FilePathEnum
 * @Author 23DAY
 * @Date 2022/9/15 20:34
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {
    /**
     * 用户头像路径
     */
    AVATAR("avatar/", "头像路径"),

    /**
     * 文章图片
     */
    ARTICLE("article/", "文章图片路径"),

    /**
     * md文件
     */
    MD("md/","md文件"),

    BLOG("/blog","博客图片");


    /**
     * 路径
     */
    private final String path;

    /**
     * 描述
     */
    private final String desc;

}
