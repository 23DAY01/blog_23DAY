package site.day.blog.strategy.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.day.blog.config.property.OssConfigProperties;

import java.io.InputStream;

/**
 * oss上传策略
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Service("ossUploadStrategyImpl")
public class OssUploadStrategyImpl extends AbstractUploadStrategyImpl {
    @Autowired
    private OssConfigProperties ossConfigProperties;

    @Override
    public Boolean exists(String uploadPath) {
        return getOssClient().doesObjectExist(ossConfigProperties.getBucketName(), uploadPath);
    }

    @Override
    public void upload(String uploadPath, InputStream inputStream) {
        getOssClient().putObject(ossConfigProperties.getBucketName(), uploadPath, inputStream);
    }

    @Override
    public String getFileAccessUrl(String uploadPath) {
        return ossConfigProperties.getUrl() + uploadPath;
    }

    /**
     * 获取ossClient
     *
     * @return {@link OSS} ossClient
     */
    private OSS getOssClient() {
        return new OSSClientBuilder().build(ossConfigProperties.getEndpoint(), ossConfigProperties.getAccessKeyId(), ossConfigProperties.getAccessKeySecret());
    }

}
