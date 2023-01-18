package site.day.blog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;

/**
 * @Description 七牛云配置
 * @ClassName QiNiuUtil
 * @Author 23DAY
 * @Date 2022/9/14 22:12
 * @Version 1.0
 */
@Component
public class QiNiuUtil {

    @Value("${qiniu.accessKey}")
    private String ACCESS_KEY;

    @Value("${qiniu.secretKey}")
    private String SECRET_KEY;

    @Value("${qiniu.bucket}")
    private String BUCKET;

    @Value("${qiniu.url}")
    public String DOMAIN_NAME;

    @SneakyThrows
    public String uploadImage(MultipartFile img, String filePath) {

        //jackson
        ObjectMapper mapper = new ObjectMapper();

        //指定带zone的配置类
        Configuration configuration = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //第二个参数为是否覆盖
        String uploadToken = auth.uploadToken(BUCKET);

        try {
            //如果不带filepath 默认用文件内容的hash作为文件名
            Response response = uploadManager.put(img.getInputStream(), filePath, uploadToken, null, null);
            DefaultPutRet putRet = mapper.readValue(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException qiniuException) {
            //throw BusinessException.withCode(StatusCode.SERVER.FILE_UPLOAD_ERROR.getCode(), StatusCode.SERVER.FILE_UPLOAD_ERROR.getMessage());
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.FILE_UPLOAD_ERROR);
        }
    }


    @SneakyThrows
    public void delImage(String key) {
        Configuration configuration = new Configuration(Region.autoRegion());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String uploadToken = auth.uploadToken(BUCKET);
        BucketManager bucketManager = new BucketManager(auth, configuration);

        try {
            Response response = bucketManager.delete(BUCKET, key);
        } catch (QiniuException qiniuException) {
            //throw BusinessException.withCode(StatusCode.SERVER.FILE_DOWNLOAD_ERROR.getCode(), StatusCode.SERVER.FILE_DOWNLOAD_ERROR.getMessage());
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.FILE_DOWNLOAD_ERROR);
        }

    }

    public String Url2Key(String url) {
        return StringUtil.removePrefix(url, DOMAIN_NAME);
    }

}
