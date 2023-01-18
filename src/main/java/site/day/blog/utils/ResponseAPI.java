package site.day.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import site.day.blog.enums.StatusCodeEnum;

/**
 * @Description 返回工具
 * @ClassName ResponseApi
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class ResponseAPI<T> {

    /**
     * 服务器响应数据
     */
    private T data;

    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private Integer code;


    private static <T> ResponseAPI<T> doResponseAPI(Integer code, String msg, T data) {
        ResponseAPI<T> api = new ResponseAPI<>();
        api.setCode(code);
        api.setData(data);
        api.setMsg(msg);
        return api;
    }

    public ResponseAPI() {
    }

    public static ResponseAPI<?> success() {
        return doResponseAPI(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), "success");
    }

    public static <T> ResponseAPI<?> success(T data) {
        return doResponseAPI(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseAPI<?> success(StatusCodeEnum success) {
        return doResponseAPI(success.getCode(), success.getMessage(), "success");
    }

    public static <T> ResponseAPI<?> success(T data, Integer code) {
        return doResponseAPI(code, StatusCodeEnum.getStatusCodeEnum(code).getMessage(), data);
    }

    public static ResponseAPI<?> fail(Integer code) {
        return doResponseAPI(code, StatusCodeEnum.getStatusCodeEnum(code).getMessage(), "fail");
    }

    public static ResponseAPI<?> fail(Integer code, String msg) {
        return doResponseAPI(code, msg, "fail");
    }

    public static ResponseAPI<?> fail(StatusCodeEnum error){
        return doResponseAPI(error.getCode(),error.getMessage(),"fail");
    }

}