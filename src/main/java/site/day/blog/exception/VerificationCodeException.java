package site.day.blog.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description 图形验证码异常
 * @ClassName VerificationCodeException
 * @Author 23DAY
 * @Date 2022/9/18 14:51
 * @Version 1.0
 */
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public VerificationCodeException(String msg) {
        super(msg);
    }
}