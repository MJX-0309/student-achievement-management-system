package edu.zhku.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.CourseQueryVo;

import java.util.List;

/**
 *
 * @author MJX
 */
public interface CourseService extends IService<Course> {

    CourseInfoVo getCourseById(Long id);

    IPage<CourseInfoVo> getCourseInfoVoPage(Long current, Long size, CourseQueryVo vo);

    void saveCourse(CourseInfoVo course);

    List<CourseInfoVo> getByTeacherId(Long id);
}
