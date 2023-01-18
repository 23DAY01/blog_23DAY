package site.day.blog.exception;


import lombok.Getter;
import site.day.blog.enums.StatusCodeEnum;

/**
 * @Description 业务异常
 * @ClassName BusinessException
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Getter
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;


    private BusinessException() {
        super();
    }

    public static BusinessException withErrorMessage(String message) {
        BusinessException businessException = new BusinessException();
        businessException.message = message;
        businessException.code = StatusCodeEnum.getStatusCodeEnum(message).getCode();
        return businessException;
    }

    public static BusinessException withErrorCodeEnum(StatusCodeEnum errorEnum) {
        BusinessException businessException = new BusinessException();
        businessException.code = errorEnum.getCode();
        businessException.message = errorEnum.getMessage();
        return businessException;
    }


}
