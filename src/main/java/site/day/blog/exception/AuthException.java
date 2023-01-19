package site.day.blog.exception;

import site.day.blog.enums.StatusCodeEnum;

/**
 * @Description
 * @ClassName AuthException
 * @Author 23DAY
 * @Date 2023/1/19 16:35
 * @Version 1.0
 */
public class AuthException extends BaseException{

    private static final long serialVersionUID = 1L;


    private AuthException() {
        super();
    }

    public static AuthException withErrorMessage(String message) {
        AuthException authException = new AuthException();
        authException.message = message;
        authException.code = StatusCodeEnum.getStatusCodeEnum(message).getCode();
        return authException;
    }

    public static AuthException withErrorCodeEnum(StatusCodeEnum errorEnum) {
        AuthException authException = new AuthException();
        authException.code = errorEnum.getCode();
        authException.message = errorEnum.getMessage();
        return authException;
    }

}
