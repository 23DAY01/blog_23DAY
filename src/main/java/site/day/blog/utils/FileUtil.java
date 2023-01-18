package site.day.blog.utils;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;


/**
 * @Description 文件工具类
 * @ClassName FileUtil
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
public class FileUtil {


    /**
     * @return java.lang.String
     * @Description 获取文件上传路径
     * @Author 23DAY
     * @Date 2022/10/12 10:17
     * @Param [java.lang.String, java.lang.String]
     **/
    public static String generateUploadPath(String originalFilename, String typePath) throws IOException {
        //uuid作为文件名
        String uuid = StringUtil.uuid().replaceAll("-" , "");
        // 获取文件扩展名
        String extName = getExtName(originalFilename);
        // 重新生成文件名
        String fileName = uuid + "." + extName;
        // 生成文件时间路径
        String timePath = generateTimePath();
        // 生成文件最终路径
        return typePath + "/" + timePath + "/" + fileName;
    }

    /**
     * @return java.lang.String
     * @Description 生成文件时间路径
     * @Author 23DAY
     * @Date 2022/10/12 9:42
     * @Param []
     **/
    public static String generateTimePath() {
        //根据日期生成路径   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        return datePath;
    }


    /**
     * @return java.io.File
     * @Description MultipartFile->File
     * @Author 23DAY
     * @Date 2022/10/12 9:43
     * @Param [org.springframework.web.multipart.MultipartFile, java.lang.String]
     **/
    @SneakyThrows
    public static File MultipartFile2File(MultipartFile multipartFile, String fileName) {
        File file = new File(fileName);
        OutputStream outputStream = new FileOutputStream(file);
        for (byte fileByte : multipartFile.getBytes()) {
            outputStream.write(fileByte);
        }
        return file;
    }


    /**
     * @Description 判断图片文件是否合法
     * @Author 23DAY
     * @Date 2022/9/15 10:11
     * @Param [org.springframework.web.multipart.MultipartFile, java.lang.String]
     **/
    public static Boolean judgeImageValid(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();

        //通过后缀名判断是否合法
        String suffix = getExtName(fileName);
        if (StringUtil.isEmpty(suffix) || Objects.equals(suffix, "")) {
            return false;
        }
        HashSet<String> allowSuffix = new HashSet<>(Arrays.asList("jpg" , "jpeg" , "png" , "gif"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
            return false;
        }

        //通过文件流头部判断文件是否合法
        File file = MultipartFile2File(multipartFile, fileName);
        String type = getType(file);
        if (!allowSuffix.contains(type)) {
            return false;
        }

        return true;
    }

    /**
     * @Description 获取文件类型
     * @Author 23DAY
     * @Date 2022/10/12 23:12
     * @Param [java.io.File]
     * @return java.lang.String
     **/
    private static String getType(File file) {
        return cn.hutool.core.io.FileUtil.getType(file);
    }


    /**
     * @return java.lang.String
     * @Description 获取文件md5值
     * @Author 23DAY
     * @Date 2022/10/12 9:45
     * @Param [java.io.InputStream]
     **/
    public static String getMd5(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(md5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @return java.lang.String
     * @Description 获取文件后缀
     * @Author 23DAY
     * @Date 2022/10/12 9:45
     * @Param [java.lang.String]
     **/
    public static String getExtName(String originalFilename) {
        return cn.hutool.core.io.FileUtil.extName(originalFilename);
    }

    public static Boolean judgeFileInvalid(MultipartFile file) {

        return true;
    }
}
