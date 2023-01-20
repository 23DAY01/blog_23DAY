package site.day.blog.constant;

/**
 * @Description
 * @ClassName StatusMsgConst
 * @Author 23DAY
 * @Date 2023/1/20 10:48
 * @Version 1.0
 */
public interface StatusMsgConst {


    String SUCCESS = "success";
    String UNKNOWN_CLIENT_ERROR="客户端未知异常";
    String UNKNOWN_RUNTIME_ERROR = "运行时未知异常";
    String UNKNOWN_SYSTEM_ERROR = "系统未知异常";

    /**
     * Client
     **/
    //        参数错误
    String ACCESS_PARAM_MISSING = "参数缺失";
    String ACCESS_PARAM_TYPE_ERROR = "参数类型错误";
    String ACCESS_PARAM_NOT_VALID = "参数校验错误";


    //        文件错误
    String FILE_EMPTY = "文件为空";
    String FILE_TYPE_ERROR = "文件类型错误";


    //        权限认证错误
    String AUTH_NO_LOGIN = "尚未登录";
    String AUTH_PERMISSION_DENIED = "缺少权限";
    String AUTH_UorP_ERROR = "账号不存在或密码错误";
    String AUTH_USERNAME_EMPTY = "用户名为空";
    String AUTH_USERNAME_NOT_FOUND = "账号不存在";
    String AUTH_FAILED = "认证失败";
    String AUTH_CODE_ERROR = "校验码错误";
    String AUTH_CODE_MISSING = "校验码为空";
    String AUTH_SESSION_TIMEOUT = "session过期";
    String AUTH_METHOD_NOT_AVAILABLE = "访问登录接口方法错误";
    String AUTH_SESSION_CONCURRENCY_MAX = "账号已在别处登录";
    String AUTH_USER_ACCOUNT_LOCKED = "用户帐号已被锁定";
    String AUTH_USER_ACCOUNT_DISABLED = "用户账号不可用";
    String AUTH_USER_ACCOUNT_EXPIRED = "用户账号已过期";
    String AUTH_USER_CREDENTIALS_EXPIRED = "用户账号密码过期";


    //        api错误
    String API_ACCESS_FREQUENT = "接口访问频繁";
    String API_REPEAT_SUBMIT = "重复提交";
    String API_ACCESS_METHOD_ERROR = "接口访问方法错误";


    /**
     * Server
     **/
    //        参数错误
    String PARAM_OPERATION_ERROR = "参数封装失败";


    //        文件错误
    String FILE_UPLOAD_ERROR = "文件上传失败";
    String FILE_DOWNLOAD_ERROR = "文件下载失败";


    //      数据库错误
    String SQL_ERROR = "数据库异常";
    String REDIS_ERROR = "redis连接异常";


}
