package site.day.blog.annotation;

import java.lang.annotation.*;

/**
 * @Description 操作日志
 * @ClassName optLog
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";

}
