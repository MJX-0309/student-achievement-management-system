package edu.zhku.boot.mapper;

import edu.zhku.boot.entity.CourseType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MJX
 * @Entity edu.zhku.boot.entity.CourseType
 */
@Mapper
public interface CourseTypeMapper extends BaseMapper<CourseType> {
    String getNameById(@Param("id") Integer id);
}




