package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.entity.Score;
import edu.zhku.boot.mapper.CourseMapper;
import edu.zhku.boot.mapper.ScoreMapper;
import edu.zhku.boot.service.ScoreService;
import edu.zhku.boot.vo.CourseStatistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MJX
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
        implements ScoreService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void saveScore(Score score) {
        Course course = courseMapper.selectById(score.getCourseId());
        calculateScore(score, course.getRegularRatio(), course.getEndtermRatio());
        this.saveOrUpdate(score, new UpdateWrapper<Score>()
                .eq("course_id", score.getCourseId())
                .and(wrapper -> wrapper.eq("student_id", score.getStudentId())));

    }


    @Override
    public void reset(Long courseId, Long studentId) {
        baseMapper.reset(courseId, studentId);
    }

    @Override
    public List<CourseStatistVo> statist(Long courseId) {
        List<Score> scores = baseMapper.selectList(new QueryWrapper<Score>().eq("course_id", courseId));
        List<CourseStatistVo> list = createStatistVoList();
        scores.forEach(score -> {
            if (score.getScore().intValue() < 60) {
                list.get(0).setCount(list.get(0).getCount()+1);
            } else if (score.getScore().intValue() < 70 && score.getScore().intValue() >= 60) {
                list.get(1).setCount(list.get(1).getCount()+1);
            } else if (score.getScore().intValue() <= 80 && score.getScore().intValue() >= 70) {
                list.get(2).setCount(list.get(2).getCount()+1);
            } else if (score.getScore().intValue() <= 90 && score.getScore().intValue() >= 80) {
                list.get(3).setCount(list.get(3).getCount()+1);
            } else {
                list.get(4).setCount(list.get(4).getCount()+1);
            }
        });
        return list;
    }

    @Override
    public void backCourse(Long courseId, Long studentId) {
        baseMapper.delete(new QueryWrapper<Score>().eq("course_id",courseId).and(wrapper->{
            wrapper.eq("student_Id",studentId);
        }));
    }




    private void calculateScore(Score score, BigDecimal regularRatio, BigDecimal endtermRatio) {
        BigDecimal regular = score.getRegularGrade().multiply(regularRatio);
        BigDecimal endterm = score.getEndtermGrade().multiply(endtermRatio);
        score.setScore(regular.add(endterm));
    }

    private List<CourseStatistVo> createStatistVoList() {
        ArrayList<CourseStatistVo> list = new ArrayList<>();
        list.add(new CourseStatistVo("不及格", 0));
        list.add(new CourseStatistVo("及格", 0));
        list.add(new CourseStatistVo("中等", 0));
        list.add(new CourseStatistVo("良好", 0));
        list.add(new CourseStatistVo("优秀", 0));
        return list;
    }
}




