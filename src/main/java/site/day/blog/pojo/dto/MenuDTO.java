package site.day.blog.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName MenuDTO
 * @Author 23DAY
 * @Date 2023/01/18 20:44
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单icon
     */
    private String icon;

    /**
     * 排序级别
     */
    private Integer orderNum;

    /**
     * 是否隐藏  0否1是
     */
    private Boolean isHidden;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 子菜单
     */
    private List<MenuDTO> childMenuList;


}
