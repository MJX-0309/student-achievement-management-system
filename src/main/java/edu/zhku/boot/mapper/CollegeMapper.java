package edu.zhku.boot.mapper;

import edu.zhku.boot.entity.College;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MJX
 */
@Mapper
public interface CollegeMapper extends BaseMapper<College> {
    String getNameById(@Param("id") Long id);
}




