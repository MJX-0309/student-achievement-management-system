package edu.zhku.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.zhku.boot.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.CourseQueryVo;
import edu.zhku.boot.vo.CourseRequestVo;

/**
 *
 * @author MJX
 */
public interface CourseService extends IService<Course> {

    CourseInfoVo getCourseById(Long id);

    IPage<CourseInfoVo> getCourseInfoVoPage(Long current, Long size, CourseQueryVo vo);

    void saveCourse(CourseRequestVo course);
}
