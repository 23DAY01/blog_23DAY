package site.day.blog.service.impl;

import site.day.blog.pojo.domain.WebsiteConfig;
import site.day.blog.mapper.WebsiteConfigMapper;
import site.day.blog.service.WebsiteConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站配置表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Service
public class WebsiteConfigServiceImpl extends ServiceImpl<WebsiteConfigMapper, WebsiteConfig> implements WebsiteConfigService {

}
