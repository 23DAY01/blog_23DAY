package site.day.blog.handler;

import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.utils.ResponseAPI;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * @Description 全局异常处理
 * @ClassName GlobalExceptionHandler
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseAPI<?> businessExceptionHandler(BusinessException e) {
        log.error("业务异常:{}" , e.getMessage());
        return ResponseAPI.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseAPI<?> sqlException(SQLException sqlException) {
        log.error("数据库异常:{}" , sqlException.getMessage());
        return ResponseAPI.fail(StatusCodeEnum.SQL_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseAPI<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        log.error("请求方法异常:" + e.getMessage());
        return ResponseAPI.fail(StatusCodeEnum.API_ACCESS_METHOD_ERROR);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseAPI<?> missingServletRequestParameterException(MissingServletRequestParameterException missingServletRequestParameterException) {
        log.error("参数缺失:{}" , missingServletRequestParameterException.getMessage());
        return ResponseAPI.fail(StatusCodeEnum.ACCESS_PARAM_MISSING);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseAPI<?> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("参数校验失败:{}" , methodArgumentNotValidException.getMessage());
        return ResponseAPI.fail(StatusCodeEnum.ACCESS_PARAM_NOT_VALID);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseAPI<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        log.error("参数类型不匹配:{}" , methodArgumentTypeMismatchException.getMessage());
        return ResponseAPI.fail(StatusCodeEnum.ACCESS_PARAM_TYPE_ERROR);
    }


    @ExceptionHandler(RedisException.class)
    public ResponseAPI<?> redisException(RedisException redisException) {
        log.error("redis异常:{}" , redisException.getMessage());
        return ResponseAPI.fail(StatusCodeEnum.REDIS_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseAPI<?> runTimeException(Exception runtimeException) {
        log.error("运行时未知异常:" + runtimeException.getMessage());
        log.error("运行时未知异常:" + Arrays.toString(runtimeException.getStackTrace()));
        return ResponseAPI.fail(StatusCodeEnum.UNKNOWN_RUNTIME_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseAPI<?> exception(Exception exception) {
        log.error("系统未知异常:" + exception.getMessage());
        log.error("系统未知异常:" + Arrays.toString(exception.getStackTrace()));
        return ResponseAPI.fail(StatusCodeEnum.UNKNOWN_SYSTEM_ERROR);
    }


}

