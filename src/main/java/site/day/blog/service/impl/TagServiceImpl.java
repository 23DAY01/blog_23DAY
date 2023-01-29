package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import site.day.blog.mapper.ArticleTagMapper;
import site.day.blog.pojo.domain.ArticleTag;
import site.day.blog.pojo.domain.Tag;
import site.day.blog.mapper.TagMapper;
import site.day.blog.pojo.dto.TagDTO;
import site.day.blog.service.ArticleTagService;
import site.day.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description Tag服务实现类
 * @ClassName TagServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleTagServiceImpl articleTagService;


    @Override
    public List<TagDTO> getTags() {
        List<Tag> tagList = tagMapper.selectList(Wrappers.emptyWrapper());
        return mapStruct.tagList2tagDTOList(tagList);
    }


    /**
     * @Description 判断携带标题是否存在并添加
     * @Author 23DAY
     * @Date 2023/1/29 18:03
     * @Param [java.lang.Integer, java.util.List<java.lang.String>]
     * @Return void
     **/
    public void saveArticleTag(Integer id, List<String> tagNameList) {
        //直接删除掉与文章有关的标签关系
        articleTagMapper.delete(Wrappers.lambdaQuery(ArticleTag.class)
                .eq(ArticleTag::getArticleId, id));
        //筛选已存在标签
        List<Tag> existTagList = tagMapper.selectList(Wrappers.lambdaQuery(Tag.class).in(Tag::getTagName, tagNameList));
        List<String> existTagNameList = existTagList.stream().map(Tag::getTagName).collect(Collectors.toList());
        List<Integer> existTagIdList = existTagList.stream().map(Tag::getId).collect(Collectors.toList());
        //去除
        tagNameList.removeAll(existTagNameList);
        //添加不存在的标签
        List<Tag> tagList = tagNameList.stream().map(tagName -> Tag.builder().tagName(tagName).build()).collect(Collectors.toList());
        saveBatch(tagList);
        List<Integer> tagIdList = tagList.stream().map(Tag::getId).collect(Collectors.toList());
        //获取文章全部标签id
        existTagIdList.addAll(tagIdList);
        List<ArticleTag> articleTagList = existTagIdList.stream().map(tagId -> ArticleTag.builder().articleId(id).tagId(tagId).build()).collect(Collectors.toList());
        articleTagService.saveBatch(articleTagList);
    }
}
