package site.day.blog.strategy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.strategy.UploadStrategy;
import site.day.blog.utils.FileUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抽象上传模板
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Service
public abstract class AbstractUploadStrategy implements UploadStrategy {

    @Override
    public String uploadFile(MultipartFile file, String typePath) {
        try {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();

            // 获取上传路径
            String uploadPath = FileUtil.generateUploadPath(originalFilename, typePath);

            //TODO:判断文件类型 并检验合法性

            Boolean fileInvalid = FileUtil.judgeFileInvalid(file);

            // 判断文件是否已存在
            if (!exists(uploadPath)) {
                // 不存在则继续上传
                upload(uploadPath, file.getInputStream());
            }
            // 返回文件访问路径
            return getFileAccessUrl(uploadPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public String uploadFile(String fileName, InputStream inputStream, String typePath) {
        try {
            String uploadPath = typePath + "/" + FileUtil.generateTimePath() + "/" + fileName;
            // 上传文件
            upload(uploadPath, inputStream);
            // 返回文件访问路径
            return getFileAccessUrl(uploadPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@link Boolean}
     */
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     *
     * @param uploadPath  路径
     * @param inputStream 输入流
     * @throws IOException io异常
     */
    public abstract void upload(String uploadPath, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问url
     *
     * @param filePath 文件路径
     * @return {@link String}
     */
    public abstract String getFileAccessUrl(String filePath);

}
