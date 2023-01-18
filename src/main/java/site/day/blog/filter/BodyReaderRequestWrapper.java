package site.day.blog.filter;

import site.day.blog.constant.WebConst;
import site.day.blog.utils.WebUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description 解决在Filter中读取Request中的流后，后续controller或restful接口中无法获取流的问题
 *              作用为：将取出来的字符串，再次转换成流，然后把它放入到新request 对象中，在chain.doFiler方法中 传递新的request对象
 * @ClassName BodyReaderRequestWrapper
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class BodyReaderRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body;

    public BodyReaderRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        request.setCharacterEncoding(WebConst.CODE.UTF8);
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getLastAccessedTime());
        System.out.println(request.getSession().getCreationTime());
        body = WebUtil.getRequestBody(request).getBytes(WebConst.CODE.UTF8);
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
            public int available() throws IOException {
                return body.length;
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }
}
