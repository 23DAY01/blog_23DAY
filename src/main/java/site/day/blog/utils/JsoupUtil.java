package site.day.blog.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.jsoup.safety.Whitelist;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description
 * @ClassName CheckUtil
 * @Author 23DAY
 * @Date 2023/1/28 23:37
 * @Version 1.0
 */
public class JsoupUtil {

    /**
     * 使用自带的basicWithImages 白名单
     * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
     * strike,strong,sub,sup,u,ul,img
     * 以及a标签的href,img标签的src,align,alt,height,width,title属性
     */
    private static final Safelist safelist = Safelist.basicWithImages();
    /**
     * 配置过滤化参数,不对代码进行格式化
     */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    static {
        /**
         * addTags() 设置白名单标签
         * addAttributes()  设置标签需要保留的属性 ,[:all]表示所有
         * preserveRelativeLinks()  是否保留元素的URL属性中的相对链接，或将它们转换为绝对链接,默认为false. 为false时将会把baseUri和元素的URL属性拼接起来
         */
        safelist.addAttributes(":all", "style");
        safelist.preserveRelativeLinks(true);
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", safelist, outputSettings);
    }


}
