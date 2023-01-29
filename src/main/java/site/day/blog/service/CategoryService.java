package site.day.blog.service;

import site.day.blog.pojo.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import site.day.blog.pojo.dto.CategoryDTO;
import site.day.blog.pojo.vo.query.CategorySaveQuery;

import java.util.List;

/**
 * @Description Category服务类
 * @ClassName CategoryService
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {

    List<CategoryDTO> getCategories();

    void saveOrUpdateCategory(CategorySaveQuery categorySaveQuery);

    void deleteCategoryByIds(List<Integer> idList);
}
