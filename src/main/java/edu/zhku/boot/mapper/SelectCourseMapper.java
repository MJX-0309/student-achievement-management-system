package edu.zhku.boot.mapper;

import edu.zhku.boot.entity.SelectCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zhku.boot.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity edu.zhku.boot.entity.SelectCourse
 */
@Mapper
public interface SelectCourseMapper extends BaseMapper<SelectCourse> {

    Long getManageTeacherIdByCourseId(@Param("courseId") Long courseId);
}




