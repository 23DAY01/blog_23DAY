package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.pojo.domain.View;
import site.day.blog.mapper.ViewMapper;
import site.day.blog.pojo.dto.ViewDTO;
import site.day.blog.service.ViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.DateUtil;
import site.day.blog.utils.MapStruct;

import java.util.Date;
import java.util.List;

/**
 * @Description View服务实现类
 * @ClassName ViewServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements ViewService {

    @Autowired
    private ViewMapper viewMapper;

    @Autowired
    private MapStruct mapStruct;

    @Override
    public List<ViewDTO> getViewsDuring(Integer ago) {
        Date startTime = DateUtil.beginOfDay(DateUtil.offsetDay(DateUtil.getNow(), -ago));
        Date endTime = DateUtil.endOfDay(DateUtil.getNow());
        List<View> viewList = viewMapper.selectList(Wrappers.lambdaQuery(View.class)
                .ge(View::getCreateTime, startTime)
                .le(View::getCreateTime, endTime)
                .orderByDesc(View::getCreateTime));
        return mapStruct.ViewList2ViewDTOList(viewList);
    }
}
