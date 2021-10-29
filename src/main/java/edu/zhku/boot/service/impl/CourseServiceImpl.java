package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.entity.SelectCourse;
import edu.zhku.boot.mapper.CollegeMapper;
import edu.zhku.boot.mapper.CourseTypeMapper;
import edu.zhku.boot.mapper.SelectCourseMapper;
import edu.zhku.boot.service.CourseService;
import edu.zhku.boot.mapper.CourseMapper;
import edu.zhku.boot.service.SelectCourseService;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.CourseQueryVo;
import edu.zhku.boot.vo.CourseRequestVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author MJX
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
implements CourseService{

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private SelectCourseMapper selectCourseMapper;
    @Override
    public CourseInfoVo getCourseById(Long id) {
        Course course = baseMapper.selectById(id);
        CourseInfoVo vo = new CourseInfoVo();
        BeanUtils.copyProperties(course,vo);
        vo.setCollege(collegeMapper.getNameById(course.getCollegeId()));
        vo.setType(courseTypeMapper.getNameById(course.getType()));
        return vo;
    }

    @Override
    public IPage<CourseInfoVo> getCourseInfoVoPage(Long current, Long size, CourseQueryVo vo) {
        Page<Course> page = new Page<>(current,size);
        Page<CourseInfoVo> voPage = new Page<>();
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (vo.getType()!=null){
            wrapper.eq("type",vo.getType());
        }
        if (vo.getCollegeId()!=null){
            wrapper.eq("college_id" ,vo.getCollegeId());
        }
        if (StringUtils.hasText(vo.getKeyword())){
            wrapper.like("name",vo.getKeyword())
                    .or()
                    .like("course_id",vo.getKeyword());
        }
        baseMapper.selectPage(page,wrapper);
        BeanUtils.copyProperties(page,voPage);
        List<CourseInfoVo> list = page.getRecords().stream().map(course -> {
            CourseInfoVo infoVo = new CourseInfoVo();
            BeanUtils.copyProperties(course, infoVo);
            infoVo.setType(courseTypeMapper.getNameById(course.getType()));
            infoVo.setCollege(collegeMapper.getNameById(course.getCourseId()));
            infoVo.setRegularRatio((int) (course.getRegularRatio().doubleValue()*100));
            infoVo.setEndtermRatio((int) (course.getEndtermRatio().doubleValue()*100));
            return infoVo;
        }).collect(Collectors.toList());
        voPage.setRecords(list);

        return voPage;
    }

    @Override
    public void saveCourse(CourseRequestVo course) {
        Course targetCourse = new Course();
        BeanUtils.copyProperties(course,targetCourse);
        baseMapper.insert(targetCourse);
        selectCourseMapper.delete(new QueryWrapper<SelectCourse>().eq("course_id",course.getCourseId()));
        Long manageTeacherId = course.getManageTeacherId();
        course.getTeacherIds().forEach(teacher->{
            SelectCourse selectCourse = new SelectCourse(teacher,course.getCourseId(),0);
            if (teacher.equals(manageTeacherId)){
                selectCourse.setIsAuthorized(1);
            }
            selectCourseMapper.insert(selectCourse);
        });
    }

    @Override
    public List<CourseInfoVo> getByTeacherId(Long id) {
        ArrayList<CourseInfoVo> list = new ArrayList<>();
        List<SelectCourse> selectCourseList = selectCourseMapper.selectList(new QueryWrapper<SelectCourse>().eq("teacher_id", id));
        selectCourseList.forEach(selectCourse->{
            Course course = baseMapper.selectById(selectCourse.getCourseId());
            CourseInfoVo infoVo = new CourseInfoVo();
            BeanUtils.copyProperties(course, infoVo);
            infoVo.setType(courseTypeMapper.getNameById(course.getType()));
            infoVo.setCollege(collegeMapper.getNameById(course.getCourseId()));
            list.add(infoVo);
        });
        return list;
    }
}




