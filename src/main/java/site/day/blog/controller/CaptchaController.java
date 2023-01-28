package site.day.blog.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.day.blog.constant.AuthConst;
import site.day.blog.constant.WebConst;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description 图片验证码
 * @ClassName CaptchaController
 * @Author 23DAY
 * @Date 2022/9/18 14:47
 * @Version 1.0
 */
@Slf4j
@Api(tags = "websiteConfig模块")
@RestController
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;


    /**
     * @Description 返回图片验证码
     * @Author 23DAY
     * @Date 2022/9/21 20:31
     * @Param [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     **/
    @ApiOperation(value = "图片验证码",notes = "返回图片验证码")
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置内容类型
        response.setContentType(WebConst.HEADER.CONTENT_TYPE_IMAGE);
        // 创建验证码文本
        String capText = captchaProducer.createText();

        // 将验证码文本设置到session
        request.getSession().setAttribute(AuthConst.CAPTCHA, capText);

        // 创建验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);
        // 获取响应输出流
        ServletOutputStream out = response.getOutputStream();
        // 将图片验证码数据写到响应输出流
        ImageIO.write(bi, "jpg", out);

        // 推送并关闭响应输出流
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}