package site.day.blog.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import site.day.blog.filter.wrapper.XssAndSqlHttpServletRequestWrapper;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.filter.wrapper.XssAndSqlHttpServletRequestWrapper;
import site.day.blog.utils.JsonUtil;
import site.day.blog.utils.ResponseAPI;
import site.day.blog.utils.WebUtil;

@Slf4j
@Component
public class XssAndSqlCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        XssAndSqlHttpServletRequestWrapper xssRequest = new XssAndSqlHttpServletRequestWrapper(request);
        String method = request.getMethod();

        String param = "";

        if ("POST".equalsIgnoreCase(method)) {
            param = this.getBodyString(xssRequest.getReader());
            if (!StringUtils.hasText(param)) {
                if (xssRequest.checkXSSAndSql(param)) {
                    WebUtil.render((HttpServletResponse) servletResponse, JsonUtil.Object2String(ResponseAPI.fail(StatusCodeEnum.SECURITY_CHECK_XSSorSQL)));
                }
            }
        }

        if (xssRequest.checkParameter()) {
            WebUtil.render((HttpServletResponse) servletResponse, JsonUtil.Object2String(ResponseAPI.fail(StatusCodeEnum.SECURITY_CHECK_XSSorSQL)));
            return;
        }
        filterChain.doFilter(xssRequest, servletResponse);
    }


    // 获取request请求body中参数
    public String getBodyString(BufferedReader br) {
        String inputLine;
        StringBuilder str = new StringBuilder();
        try {
            while ((inputLine = br.readLine()) != null) {
                str.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            log.error("IOException: ", e);
        }
        return str.toString();

    }

}