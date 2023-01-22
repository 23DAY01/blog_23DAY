package site.day.blog.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import site.day.blog.constant.WebConst;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


/**
 * @Description 加密工具类
 * @ClassName EncryptUtil
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class EncryptUtil {

    /**
     * 创建aes
     * @param mode 加密类型
     * @param padding
     * @return
     */
    private static AES createAes(Mode mode, Padding padding) {
        AES aes;
        if (Mode.CBC == mode) {
            aes = new AES(mode, padding,
                    new SecretKeySpec(WebConst.SECURITY.ENCODE_KEY.getBytes(), "AES"),
                    new IvParameterSpec(WebConst.SECURITY.IV_KEY.getBytes()));
        } else {
            aes = new AES(mode, padding,
                    new SecretKeySpec(WebConst.SECURITY.ENCODE_KEY.getBytes(), "AES"));
        }
        return aes;
    }

    /**
     * aes加密
     * @param data 加密字符串
     * @param mode 加密类型
     * @param padding
     * @return
     */
    public static String enAes(String data, Mode mode, Padding padding) {
        AES aes = createAes(mode, padding);
        return aes.encryptBase64(data, StandardCharsets.UTF_8);
    }

    /**
     * aes解密
     * @param data 解密字符串
     * @param mode 解密类型
     * @param padding
     * @return
     */
    public static String deAes(String data, Mode mode, Padding padding) {
        AES aes = createAes(mode, padding);
        byte[] decryptDataBase64 = aes.decrypt(data);
        return new String(decryptDataBase64, StandardCharsets.UTF_8);
    }



}
