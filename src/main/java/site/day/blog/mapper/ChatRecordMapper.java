package site.day.blog.mapper;

import site.day.blog.pojo.domain.ChatRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天记录表 Mapper 接口
 * </p>
 *
 * @author 23DAY
 * @since 2023/01/18 20:44
 */
@Mapper
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

}
