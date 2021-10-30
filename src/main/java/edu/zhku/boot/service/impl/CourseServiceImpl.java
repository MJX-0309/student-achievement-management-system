package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.entity.Score;
import edu.zhku.boot.entity.SelectCourse;
import edu.zhku.boot.entity.Teacher;
import edu.zhku.boot.mapper.*;
import edu.zhku.boot.service.CourseService;
import edu.zhku.boot.service.ScoreService;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.CourseQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private ScoreService scoreService;
    @Override
    public CourseInfoVo getCourseById(Long id) {
        Course course = baseMapper.selectById(id);
        CourseInfoVo vo = new CourseInfoVo();
        BeanUtils.copyProperties(course,vo);
        vo.setCollegeName(collegeMapper.getNameById(course.getCollegeId()));
        vo.setTypeName(courseTypeMapper.getNameById(course.getTypeId()));
        vo.setTypeId(course.getTypeId());
        Long manageTeacherId = selectCourseMapper.getManageTeacherIdByCourseId(course.getCourseId());
        vo.setManagerTeacher(teacherMapper.selectById(manageTeacherId));
        List<SelectCourse> selectCourseList = selectCourseMapper.selectList(new QueryWrapper<SelectCourse>().eq("course_id", course.getCourseId()));
        ArrayList<Teacher> teachers = new ArrayList<>();
        selectCourseList.forEach(selectCourse -> {
            Teacher teacher = teacherMapper.selectById(selectCourse.getTeacherId());
            teachers.add(teacher);
        });
        vo.setTeachers(teachers);
        vo.setRegularRatio((int) (course.getRegularRatio().doubleValue()*100));
        vo.setEndtermRatio((int) (course.getEndtermRatio().doubleValue()*100));
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
            infoVo.setTypeName(courseTypeMapper.getNameById(course.getTypeId()));
            infoVo.setTypeId(course.getTypeId());
            infoVo.setCollegeName(collegeMapper.getNameById(course.getCourseId()));
            Long manageTeacherId = selectCourseMapper.getManageTeacherIdByCourseId(course.getCourseId());
            infoVo.setManagerTeacher(teacherMapper.selectById(manageTeacherId));
            List<SelectCourse> selectCourseList = selectCourseMapper.selectList(new QueryWrapper<SelectCourse>().eq("course_id", course.getCourseId()));
            ArrayList<Teacher> teachers = new ArrayList<>();
            selectCourseList.forEach(selectCourse -> {
                Teacher teacher = teacherMapper.selectById(selectCourse.getTeacherId());
                teachers.add(teacher);
            });
            infoVo.setTeachers(teachers);
            infoVo.setRegularRatio((int) (course.getRegularRatio().doubleValue()*100));
            infoVo.setEndtermRatio((int) (course.getEndtermRatio().doubleValue()*100));
            return infoVo;
        }).collect(Collectors.toList());
        voPage.setRecords(list);

        return voPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCourse(CourseInfoVo course) {
        Course targetCourse = new Course();
        BeanUtils.copyProperties(course,targetCourse);
        targetCourse.setRegularRatio(new BigDecimal(course.getRegularRatio()).divide(new BigDecimal("100")));
        targetCourse.setEndtermRatio(new BigDecimal(course.getEndtermRatio()).divide(new BigDecimal("100")));
        Course oldCourse = baseMapper.selectById(course.getCourseId());
        //修改了成绩比例
        if (oldCourse!=null&&(!oldCourse.getRegularRatio().equals(targetCourse.getRegularRatio()))){
            CompletableFuture.runAsync(()->{
                List<Score> scores = scoreMapper.selectList(new QueryWrapper<Score>().eq("course_id", oldCourse.getCourseId()));
                for (Score score : scores) {
                    BigDecimal regular = score.getRegularGrade().multiply(targetCourse.getRegularRatio());
                    BigDecimal endterm = score.getEndtermGrade().multiply(targetCourse.getEndtermRatio());
                    score.setScore(regular.add(endterm));
                    scoreMapper.update(score,
                            new UpdateWrapper<Score>().eq("student_id",score.getStudentId())
                            .and(wrapper->{wrapper.eq("course_id",score.getCourseId());}));
             }
            });
        }
        this.saveOrUpdate(targetCourse);
        selectCourseMapper.delete(new QueryWrapper<SelectCourse>().eq("course_id",course.getCourseId()));
        Teacher managerTeacher = course.getManagerTeacher();
        selectCourseMapper.insert(new SelectCourse(managerTeacher.getTeacherId(),course.getCourseId(),1));
        for (Teacher teacher : course.getTeachers()) {
            SelectCourse selectCourse = new SelectCourse(teacher.getTeacherId(),course.getCourseId(),0);
            if (teacher.getTeacherId().equals(managerTeacher.getTeacherId())){
                continue;
            }
            selectCourseMapper.insert(selectCourse);
        }
    }

    @Override
    public List<CourseInfoVo> getByTeacherId(Long id) {
        ArrayList<CourseInfoVo> list = new ArrayList<>();
        List<SelectCourse> selectCourseList = selectCourseMapper.selectList(new QueryWrapper<SelectCourse>().eq("teacher_id", id));
        selectCourseList.forEach(selectCourse -> {
            Course course = baseMapper.selectById(selectCourse.getCourseId());
            CourseInfoVo courseInfoVo = new CourseInfoVo();
            BeanUtils.copyProperties(course,courseInfoVo);
            Teacher managerTeacher = teacherMapper.selectById(selectCourse.getTeacherId());
            courseInfoVo.setManagerTeacher(managerTeacher);
            courseInfoVo.setCollegeName(collegeMapper.getNameById(course.getCourseId()));
            courseInfoVo.setTypeName(courseTypeMapper.getNameById(course.getTypeId()));
            List<SelectCourse> selectCourseList2 = selectCourseMapper.selectList(new QueryWrapper<SelectCourse>().eq("course_id", course.getCourseId()));
            ArrayList<Teacher> teachers = new ArrayList<>();
            selectCourseList2.forEach(selectCourse2 -> {
                Teacher teacher = teacherMapper.selectById(selectCourse2.getTeacherId());
                teachers.add(teacher);
            });
            courseInfoVo.setTeachers(teachers);
            courseInfoVo.setRegularRatio((int) (course.getRegularRatio().doubleValue()*100));
            courseInfoVo.setEndtermRatio((int) (course.getEndtermRatio().doubleValue()*100));
            list.add(courseInfoVo);
        });
        return list;
    }
}




