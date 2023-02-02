package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.pojo.domain.Page;
import site.day.blog.mapper.PageMapper;
import site.day.blog.pojo.dto.PageDTO;
import site.day.blog.pojo.vo.query.PageSaveQuery;
import site.day.blog.service.PageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;

import java.util.List;
import java.util.Map;

/**
 * @Description Page服务实现类
 * @ClassName PageServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private MapStruct mapStruct;

    @Override
    public List<PageDTO> getPages() {
        List<Page> pageList = pageMapper.selectList(Wrappers.emptyWrapper());
        return mapStruct.PageList2PageDTOList(pageList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdatePage(PageSaveQuery pageSaveQuery) {
        Page page = mapStruct.PageSaveQuery2Page(pageSaveQuery);
        saveOrUpdate(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePageById(Integer id) {
        pageMapper.deleteById(id);
    }

}
