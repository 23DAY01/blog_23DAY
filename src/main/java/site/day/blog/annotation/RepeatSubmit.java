package site.day.blog.annotation;

import java.lang.annotation.*;

/**
 * @Description 重复提交
 * @ClassName RepeatSubmit
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit
{
    /**
     * 间隔时间(ms)，小于此时间视为重复提交
     */
    public int interval() default 5000;
}
