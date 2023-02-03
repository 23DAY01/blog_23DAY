package site.day.blog.filter.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private String key = "and|exec|insert|select|delete|drop|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
    private Set<String> disallowedKeyWords = new HashSet<String>(0);

    HttpServletRequest orgRequest = null;
    private Map<String, String[]> parameterMap;
    private final byte[] body; //用于保存读取body中数据

    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        orgRequest = request;
        parameterMap = request.getParameterMap();
        body = StreamUtils.copyToByteArray(request.getInputStream());

        String[] keyStr = key.split("\\|");
        disallowedKeyWords.addAll(Arrays.asList(keyStr));

    }

    //重写方法进行注入检测


    /**
     * @return java.util.Enumeration<java.lang.String>
     * @Description 获取所有参数名
     * @Author 23DAY
     * @Date 2023/2/3 15:57
     * @Param []
     **/
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(parameterMap.keySet());
        return vector.elements();
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss 和 sql过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        if (results == null || results.length == 0)
            return null;
        else {
            String value = results[0];
            if (value != null) {
                value = xssEncode(value);
            }
            return value;
        }
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据 接收数组变量 ，如checkbox类型
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] results = parameterMap.get(name);
        if (results == null || results.length == 0)
            return null;
        else {
            int length = results.length;
            for (int i = 0; i < length; i++) {
                results[i] = xssEncode(results[i]);
            }
            return results;
        }
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss和sql过滤
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {

        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * @return java.lang.String
     * @Description 将容易引起xss和sql漏洞的半角字符直接替换成全角字符
     * @Author 23DAY
     * @Date 2023/2/3 15:58
     * @Param [java.lang.String]
     **/
    private String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } else {
            s = stripXSSAndSql(s);
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append("＞");// 转义大于号
                    break;
                case '<':
                    sb.append("＜");// 转义小于号
                    break;
                case '\'':
                    sb.append("＇");// 转义单引号
                    break;
                case '\"':
                    sb.append("＂");// 转义双引号
                    break;
                case '&':
                    sb.append("＆");// 转义&
                    break;
                case '#':
                    sb.append("＃");// 转义#
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    //获取最原始的request
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    //获取最原始的request的静态方法
    public HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssAndSqlHttpServletRequestWrapper) {
            return ((XssAndSqlHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }

    /**
     * @Return java.lang.String
     * @Description 防止xss跨脚本攻击（替换，根据实际情况调整）
     * @Author 23DAY
     * @Date 2023/2/3 16:00
     * @Param [java.lang.String]
     **/
    public String stripXSSAndSql(String value) {
        if (value != null) {
            Pattern scriptPattern = Pattern.compile(
                    "<[\r\n| | ]*script[\r\n| | ]*>(.*?)<!--[\r\n| | ]*script[\r\n| | ]*-->", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of e-xpression
            scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome tag
            scriptPattern = Pattern.compile("<!--[\r\n| | ]*script[\r\n| | ]*-->", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid e-xpression(...) expressions
            scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid οnlοad= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }


    public boolean checkXSSAndSql(String value) {
        boolean flag = false;
        if (value != null) {
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile(
                    "<[\r\n| | ]*script[\r\n| | ]*>(.*?)</[\r\n| | ]*script[\r\n| | ]*>", Pattern.CASE_INSENSITIVE);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Avoid anything in a
            // src="http://www.yihaomen.com/article/java/..." type of
            // e-xpression
            scriptPattern = Pattern.compile("src[\r\n| | ]*=[\r\n| | ]*[\\\"|\\\'](.*?)[\\\"|\\\']",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("<!--[\r\n| | ]*script[\r\n| | ]*-->", Pattern.CASE_INSENSITIVE);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<[\r\n| | ]*script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Avoid e-xpression(...) expressions
            scriptPattern = Pattern.compile("e-xpression\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }
            // Avoid οnlοad= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            flag = scriptPattern.matcher(value).find();
            if (flag) {
                return flag;
            }

            flag = checkSqlKeyWords(value);
        }
        return flag;
    }

    public boolean checkSqlKeyWords(String value) {
        String paramValue = value.toLowerCase();//转成小写
        for (String keyword : disallowedKeyWords) {
            if (paramValue.length() > keyword.length() + 4
                    && (paramValue.contains(" " + keyword) || paramValue.contains(keyword + " ") || paramValue.contains(" " + keyword + " "))) {
                log.error(this.getRequestURI() + "参数中包含不允许sql的关键词(" + keyword
                        + ")");
                return true;
            }
        }
        return false;
    }

    public final boolean checkParameter() {

        Map<String, String[]> submitParams = new HashMap(parameterMap);

        Set<String> submitNames = submitParams.keySet();
        for (String submitName : submitNames) {
            Object submitValues = submitParams.get(submitName);
            if ((submitValues instanceof String)) {
                if (checkXSSAndSql((String) submitValues)) {
                    return true;
                }
            } else if ((submitValues instanceof String[])) {
                for (String submitValue : (String[]) submitValues) {
                    if (checkXSSAndSql(submitValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isReady() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {
                // TODO Auto-generated method stub

            }
        };
    }

}