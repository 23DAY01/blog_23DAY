package site.day.blog.mapper;

import site.day.blog.pojo.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 23DAY
 * @since 2023-01-17
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
