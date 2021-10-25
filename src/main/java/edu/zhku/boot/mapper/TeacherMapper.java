package edu.zhku.boot.mapper;

import edu.zhku.boot.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Entity edu.zhku.boot.entity.Teacher
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    String getNameById(Long id);
}




