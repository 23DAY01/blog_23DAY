package site.day.blog.listener;

import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.Arrays;

import static site.day.blog.constant.ArticleConst.VISITOR_ARTICLE_LIST;

/**
 * @Description
 * @ClassName RedisHttpSessionListener
 * @Author 23DAY
 * @Date 2023/1/27 18:53
 * @Version 1.0
 */
public class RedisHttpSessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSessionAttributeListener.super.attributeAdded(se);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        HttpSessionAttributeListener.super.attributeRemoved(se);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        HttpSessionAttributeListener.super.attributeReplaced(se);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //记录游客在一个session内访问文章id
        se.getSession().setAttribute(VISITOR_ARTICLE_LIST, new ArrayList<Integer>());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().removeAttribute(VISITOR_ARTICLE_LIST);
    }
}
