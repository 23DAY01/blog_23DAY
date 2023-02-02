package site.day.blog.strategy.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.day.blog.enums.FileExtEnum;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.strategy.service.AbstractUploadStrategy;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

/**
 * 本地上传策略
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Service("localUploadStrategyImpl")
public class LocalUploadStrategy extends AbstractUploadStrategy {

    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;

    /**
     * 访问url
     */
    @Value("${upload.local.url}")
    private String localUrl;

    @Override
    public Boolean exists(String filePath) {
        return new File(localPath + filePath).exists();
    }

    @Override
    public void upload(String uploadPath, InputStream inputStream) throws IOException {
        // 获取文件路径
        String path = uploadPath.substring(0, uploadPath.lastIndexOf("/") + 1);
        // 判断目录是否存在
        File directory = new File(localPath + path);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw BusinessException.withErrorCodeEnum(StatusCodeEnum.FILE_UPLOAD_ERROR);
            }
        }
        // 写入文件
        File file = new File(localPath + uploadPath);
        String ext = "." + uploadPath.split("\\.")[1];

        switch (Objects.requireNonNull(FileExtEnum.getFileExt(ext))) {
            case MD:
            case TXT:
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                while (reader.ready()) {
                    writer.write((char) reader.read());
                }
                writer.flush();
                writer.close();
                reader.close();
                break;
            default:
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
                byte[] bytes = new byte[1024];
                int length;
                while ((length = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, length);
                }
                bos.flush();
                bos.close();
                bis.close();
                break;
        }
        inputStream.close();
    }


    @Override
    public String getFileAccessUrl(String filePath) {
        return localUrl + filePath;
    }

}
