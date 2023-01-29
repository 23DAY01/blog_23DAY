package site.day.blog.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import site.day.blog.constant.StatusMsgConst;


/**
 * @Description 业务状态码枚举
 * regex：([A-Z\w]+)\(([0-9]+),\s".+"\),
 * $1
 * @ClassName StatusCode
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {


    SUCCESS(2000101, StatusMsgConst.SUCCESS),
    UNKNOWN_CLIENT_ERROR(4009901, StatusMsgConst.UNKNOWN_CLIENT_ERROR),
    UNKNOWN_RUNTIME_ERROR(5009901, StatusMsgConst.UNKNOWN_RUNTIME_ERROR),
    UNKNOWN_SYSTEM_ERROR(5009902, StatusMsgConst.UNKNOWN_SYSTEM_ERROR),

    /**
     * Client
     **/
    //        参数错误
    ACCESS_PARAM_MISSING(4000101, StatusMsgConst.ACCESS_PARAM_MISSING),
    ACCESS_PARAM_TYPE_ERROR(4000102, StatusMsgConst.ACCESS_PARAM_TYPE_ERROR),
    ACCESS_PARAM_NOT_VALID(4000103, StatusMsgConst.ACCESS_PARAM_NOT_VALID),


    //        文件错误
    FILE_EMPTY(4000201, StatusMsgConst.FILE_EMPTY),
    FILE_TYPE_ERROR(4000202, StatusMsgConst.FILE_TYPE_ERROR),


    //        权限认证错误
    AUTH_NO_LOGIN(4000301, StatusMsgConst.AUTH_NO_LOGIN),
    AUTH_PERMISSION_DENIED(4000302, StatusMsgConst.AUTH_PERMISSION_DENIED),
    AUTH_UorP_ERROR(4000303, StatusMsgConst.AUTH_UorP_ERROR),
    AUTH_USERNAME_EMPTY(4000304, StatusMsgConst.AUTH_USERNAME_EMPTY),
    AUTH_USERNAME_NOT_FOUND(4000305, StatusMsgConst.AUTH_USERNAME_NOT_FOUND),
    AUTH_FAILED(4000306, StatusMsgConst.AUTH_FAILED),
    AUTH_CODE_ERROR(4000307, StatusMsgConst.AUTH_CODE_ERROR),
    AUTH_CODE_MISSING(4000308, StatusMsgConst.AUTH_CODE_MISSING),
    AUTH_SESSION_TIMEOUT(4000309, StatusMsgConst.AUTH_SESSION_TIMEOUT),
    AUTH_METHOD_NOT_AVAILABLE(4000310, StatusMsgConst.AUTH_METHOD_NOT_AVAILABLE),
    AUTH_SESSION_CONCURRENCY_MAX(4000311, StatusMsgConst.AUTH_SESSION_CONCURRENCY_MAX),
    AUTH_USER_ACCOUNT_LOCKED(4000312, StatusMsgConst.AUTH_USER_ACCOUNT_LOCKED),
    AUTH_USER_ACCOUNT_DISABLED(4000313, StatusMsgConst.AUTH_USER_ACCOUNT_DISABLED),
    AUTH_USER_ACCOUNT_EXPIRED(4000314, StatusMsgConst.AUTH_USER_ACCOUNT_EXPIRED),
    AUTH_USER_CREDENTIALS_EXPIRED(4000315, StatusMsgConst.AUTH_USER_CREDENTIALS_EXPIRED),
    AUTH_USER_USERNAME_REPEAT(4000316, StatusMsgConst.AUTH_USER_USERNAME_REPEAT),

    //        api错误
    API_ACCESS_FREQUENT(4000401, StatusMsgConst.API_ACCESS_FREQUENT),
    API_REPEAT_SUBMIT(4000402, StatusMsgConst.API_REPEAT_SUBMIT),
    API_ACCESS_METHOD_ERROR(4000403, StatusMsgConst.API_ACCESS_METHOD_ERROR),

    //业务错误-article
    ARTICLE_MISSING(4001001, StatusMsgConst.ARTICLE_MISSING),
    //业务错误-talk
    TALK_MISSING(4001101, StatusMsgConst.TALK_MISSING),
    //业务错误-category
    CATEGORY_NAME_REPEAT(4001201, StatusMsgConst.CATEGORY_NAME_REPEAT),
    CATEGORY_MISSING(4001202, StatusMsgConst.CATEGORY_MISSING),
    CATEGORY_HAVE_ARTICLE(4001203,StatusMsgConst.CATEGORY_HAVE_ARTICLE),
    //业务错误-tag
    TAG_NAME_REPEAT(4001301, StatusMsgConst.TAG_NAME_REPEAT),
    TAG_MISSING(4001302, StatusMsgConst.TAG_MISSING),

    /**
     * Server
     **/
    //        参数错误
    PARAM_OPERATION_ERROR(5000101, StatusMsgConst.PARAM_OPERATION_ERROR),

    //        文件错误
    FILE_UPLOAD_ERROR(5000201, StatusMsgConst.FILE_UPLOAD_ERROR),
    FILE_DOWNLOAD_ERROR(5000202, StatusMsgConst.FILE_DOWNLOAD_ERROR),

    //      数据库错误
    SQL_ERROR(5000401, StatusMsgConst.SQL_ERROR),
    REDIS_ERROR(5000402, StatusMsgConst.REDIS_ERROR);


    private final Integer code;

    private final String message;


    /**
     * @return site.day.blog.enums.StatusCodeEnum
     * @Description 通过code获取StatusCodeEnum
     * @Author 23DAY
     * @Date 2022/10/12 15:09
     * @Param [java.lang.Integer]
     **/
    public static StatusCodeEnum getStatusCodeEnum(Integer code) {
        for (StatusCodeEnum value : StatusCodeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return UNKNOWN_RUNTIME_ERROR;
    }

    /**
     * @return site.day.blog.enums.StatusCodeEnum
     * @Description 通过message获取StatusCodeEnum
     * @Author 23DAY
     * @Date 2022/10/12 15:09
     * @Param [java.lang.Integer]
     **/
    public static StatusCodeEnum getStatusCodeEnum(String message) {
        for (StatusCodeEnum value : StatusCodeEnum.values()) {
            if (value.getMessage().equals(message)) {
                return value;
            }
        }
        return UNKNOWN_RUNTIME_ERROR;
    }
}
