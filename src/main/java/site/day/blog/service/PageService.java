package site.day.blog.service;

import site.day.blog.pojo.domain.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.PageDTO;
import site.day.blog.pojo.vo.query.PageSaveQuery;

import java.util.List;

/**
 * @Description Page服务类
 * @ClassName PageService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface PageService extends IService<Page> {

    List<PageDTO> getPages();

    void saveOrUpdatePage(PageSaveQuery pageSaveQuery);

    void deletePageById(Integer id);
}
