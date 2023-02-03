package site.day.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import site.day.blog.enums.StatusCodeEnum;
import site.day.blog.exception.BusinessException;
import site.day.blog.mapper.ArticleMapper;
import site.day.blog.pojo.domain.Article;
import site.day.blog.pojo.domain.Category;
import site.day.blog.mapper.CategoryMapper;
import site.day.blog.pojo.dto.CategoryDTO;
import site.day.blog.pojo.vo.query.CategorySaveQuery;
import site.day.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.day.blog.utils.MapStruct;
import site.day.blog.utils.PageUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description Category服务实现类
 * @ClassName CategoryServiceImpl
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private ArticleMapper articleMapper;


    @Cacheable(cacheNames = "category", sync = true)
    @Override
    public List<CategoryDTO> getCategories() {
        Page<Category> categoryPage = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<Category> categoryList = categoryMapper.selectPage(categoryPage, Wrappers.emptyWrapper()).getRecords();
        //设置分页参数
        PageUtil.setTotal(categoryPage.getTotal());
        //类型转换
        List<CategoryDTO> categoryDTOList = mapStruct.CategoryList2CategoryDTOList(categoryList);
        //注入
        addArticleInfo(categoryDTOList);
        return categoryDTOList;
    }

    @CachePut(cacheNames = "category")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateCategory(CategorySaveQuery query) {
        Integer categoryId = categoryMapper.selectOne(Wrappers.lambdaQuery(Category.class)
                .eq(Category::getCategoryName, query.getCategoryName())).getId();
        //如果已存在
        if (Objects.nonNull(categoryId) && !categoryId.equals(query.getId())) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.CATEGORY_NAME_REPEAT);
        }
        Category category = mapStruct.CategorySaveQuery2Category(query);
        saveOrUpdate(category);
    }

    @CacheEvict(cacheNames = "category",allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCategoryByIds(List<Integer> idList) {
        long count = articleMapper.selectCount(Wrappers.lambdaQuery(Article.class)
                .in(Article::getCategoryId, idList));
        //分类下存在文章不可删除
        if (count > 0) {
            throw BusinessException.withErrorCodeEnum(StatusCodeEnum.CATEGORY_ARTICLE_RELATION);
        }
        categoryMapper.deleteBatchIds(idList);
    }

    /**
     * @Description 封装文章信息
     * @Author 23DAY
     * @Date 2023/1/29 16:40
     * @Param [java.util.List<site.day.blog.pojo.dto.CategoryDTO>]
     * @Return void
     **/
    public void addArticleInfo(List<CategoryDTO> categoryDTOList) {
        //获取categoryId到articleIdList的映射
        Map<Integer, List<Integer>> categoryId2ArticleIdMap = articleMapper.selectList(Wrappers.emptyWrapper())
                .stream().collect(Collectors.groupingBy(Article::getCategoryId, Collectors.mapping(Article::getId, Collectors.toList())));
        //根据id取出来articleIdList的size
        for (CategoryDTO categoryDTO : categoryDTOList) {
            categoryDTO.setArticleCount(categoryId2ArticleIdMap.get(categoryDTO.getId()).size());
        }
    }


    /**
     * @Description 判断新增文章携带分类是否存在并返回
     * @Author 23DAY
     * @Date 2023/1/29 16:46
     * @Param [java.lang.String]
     * @Return site.day.blog.pojo.domain.Category
     **/
    @CachePut(cacheNames = "category")
    public Integer saveArticleCategory(String categoryName) {
        Category category = categoryMapper.selectOne(Wrappers.lambdaQuery(Category.class)
                .eq(Category::getCategoryName, categoryName));
        if (Objects.isNull(category)) {
            category = Category.builder()
                    .categoryName(categoryName)
                    .build();
            categoryMapper.insert(category);
        }
        return category.getId();
    }
}
