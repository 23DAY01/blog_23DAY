package site.day.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 基础异常
 * @ClassName BaseException
 * @Author 23DAY
 * @Date 2022/11/2 19:07
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer code;
    protected String message;

}
