package site.day.blog.interceptor;

import com.aliyun.oss.common.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.HandlerInterceptor;
import site.day.blog.utils.PageUtil;
import site.day.blog.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static site.day.blog.constant.CommonConst.*;

/**
 * @Description
 * @ClassName PageableIntercept
 * @Author 23DAY
 * @Date 2023/1/25 22:12
 * @Version 1.0
 */
public class PageableInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //如果current为空或null则不创建page
        boolean flag = StringUtil.isNullOrEmpty(request.getParameter(PAGE_CURRENT));
        String current = StringUtil.isNullOrEmpty(request.getParameter(PAGE_CURRENT)) ? String.valueOf(PAGE_DEFAULT_CURRENT) : request.getParameter(PAGE_CURRENT);
        String size = StringUtil.isNullOrEmpty(request.getParameter(PAGE_SIZE)) ? String.valueOf(PAGE_DEFAULT_SIZE) : request.getParameter(PAGE_SIZE);
        if (!flag){
            PageUtil.setPage(new Page<>(Long.parseLong(current), Long.parseLong(size)));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        PageUtil.remove();
    }

}
