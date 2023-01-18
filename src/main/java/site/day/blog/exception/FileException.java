package site.day.blog.exception;

import site.day.blog.enums.StatusCodeEnum;

/**
 * @Description 文件异常
 * @ClassName FileException
 * @Author 23DAY
 * @Date 2022/11/2 19:48
 * @Version 1.0
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;


    private FileException() {
        super();
    }

    public static FileException withErrorMessage(String message) {
        FileException fileException = new FileException();
        fileException.message = message;
        fileException.code = StatusCodeEnum.getStatusCodeEnum(message).getCode();
        return fileException;
    }

    public static FileException withErrorCodeEnum(StatusCodeEnum errorEnum) {
        FileException fileException = new FileException();
        fileException.code = errorEnum.getCode();
        fileException.message = errorEnum.getMessage();
        return fileException;
    }
}
