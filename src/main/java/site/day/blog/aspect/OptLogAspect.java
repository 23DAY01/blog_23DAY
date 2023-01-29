package site.day.blog.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import site.day.blog.service.OperationLogService;
import site.day.blog.annotation.OptLog;
import site.day.blog.pojo.domain.OperationLog;
import site.day.blog.utils.AuthUtil;
import site.day.blog.utils.JsonUtil;
import site.day.blog.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;


/**
 * @Description 操作日志 一定要鉴权，否则会出bug
 * @ClassName OptLogAspect
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Aspect
@Component
public class OptLogAspect {

    @Autowired
    private OperationLogService operationLogService;


    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(site.day.blog.annotation.OptLog)")
    public void optLogPointCut() {}


    /**
     * @Description 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     * @Author 23DAY
     * @Date 2022/9/21 20:09
     * @Param [org.aspectj.lang.JoinPoint, java.lang.Object]
     **/
    @AfterReturning(value = "optLogPointCut()", returning = "keys")
    public void saveOptLog(JoinPoint joinPoint, Object keys) throws JsonProcessingException {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperationLog operationLog = new OperationLog();
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        OptLog optLog = method.getAnnotation(OptLog.class);
        // 操作模块
        operationLog.setOptModule(api.tags()[0]);
        // 操作类型
        operationLog.setOptType(optLog.optType());
        // 操作描述
        operationLog.setOptDesc(apiOperation.value());
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;
        // 请求方式
        operationLog.setRequestMethod(Objects.requireNonNull(request).getMethod());
        // 请求方法
        operationLog.setOptMethod(methodName);
        // 请求参数
        operationLog.setRequestParam(JsonUtil.Object2String(joinPoint.getArgs()));
        // 返回结果
        operationLog.setResponseData(JsonUtil.Object2String(keys));
        // 请求用户ID
        operationLog.setUserId(AuthUtil.getLoginUser().getUserInfo().getId());
        // 请求用户
        operationLog.setNickname(AuthUtil.getLoginUser().getUserInfo().getNickname());
        // 请求IP
        String ipAddress = WebUtil.getIpAddress(request);
        operationLog.setIpAddress(ipAddress);
        operationLog.setIpSource(WebUtil.getIpSource(ipAddress));
        // 请求URL
        operationLog.setOptUrl(request.getRequestURI());
        operationLogService.save(operationLog);
    }

}
