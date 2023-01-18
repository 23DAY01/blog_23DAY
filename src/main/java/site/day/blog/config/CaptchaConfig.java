package site.day.blog.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.Random;

import static com.google.code.kaptcha.Constants.*;

/**
 * @Description 图形验证码配置
 * @ClassName CaptchaConfig
 * @Author 23DAY
 * @Date 2022/9/18 14:46
 * @Version 1.0
 */
@Configuration
public class CaptchaConfig extends DefaultTextCreator {

    private static final String[] CNUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

    /**
     * @Description Kaptcha文本生成器
     * @Author 23DAY
     * @Date 2022/10/18 19:34
     * @Param []
     * @return java.lang.String
     **/
    @Override
    public String getText() {
        Integer result = 0;
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder suChinese = new StringBuilder();
        int randomoperands = random.nextInt(3);
        if (randomoperands == 0) {
            result = x * y;
            suChinese.append(CNUMBERS[x]);
            suChinese.append("*");
            suChinese.append(CNUMBERS[y]);
        } else if (randomoperands == 1) {
            if ((x != 0) && y % x == 0) {
                result = y / x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("/");
                suChinese.append(CNUMBERS[x]);
            } else {
                result = x + y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("+");
                suChinese.append(CNUMBERS[y]);
            }
        } else {
            if (x >= y) {
                result = x - y;
                suChinese.append(CNUMBERS[x]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[y]);
            } else {
                result = y - x;
                suChinese.append(CNUMBERS[y]);
                suChinese.append("-");
                suChinese.append(CNUMBERS[x]);
            }
        }
        suChinese.append("=?@").append(result);
        return suChinese.toString();
    }

    @Bean
    public DefaultKaptcha captchaProducer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
//        // 是否有边框 默认为true 我们可以自己设置yes，no
//        properties.setProperty(KAPTCHA_BORDER, "yes");
//        // 验证码文本字符颜色 默认为Color.BLACK
//        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
//        // 验证码图片宽度 默认为200
//        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
//        // 验证码图片高度 默认为50
//        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
//        // 验证码文本字符大小 默认为40
//        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
//        // KAPTCHA_SESSION_KEY
//        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
//        // 验证码文本字符长度 默认为5
//        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
//        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
//        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
//        // 图片样式
//        // 水纹com.google.code.kaptcha.impl.WaterRipple
//        // 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
//        // 阴影com.google.code.kaptcha.impl.ShadowGimpy
//        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");

        // 图片宽度
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "150");
        // 图片长度
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "50");
        // 字符集
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
        // 字符长度
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");


        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


}