package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Major;
import edu.zhku.boot.entity.Score;
import edu.zhku.boot.entity.Student;
import edu.zhku.boot.mapper.CollegeMapper;
import edu.zhku.boot.mapper.MajorMapper;
import edu.zhku.boot.mapper.ScoreMapper;
import edu.zhku.boot.service.StudentService;
import edu.zhku.boot.mapper.StudentMapper;
import edu.zhku.boot.vo.StudentInfoVo;
import edu.zhku.boot.vo.StudentQueryVo;
import edu.zhku.boot.vo.StudentScoreVo;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
implements StudentService{

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public StudentInfoVo getStudentById(Long id) {
        Student student = baseMapper.selectById(id);
        StudentInfoVo infoVo = new StudentInfoVo();
        BeanUtils.copyProperties(student,infoVo);
        infoVo.setMajor(majorMapper.getNameByiId(student.getMajorId()));
        return infoVo;
    }

    @Override
    public IPage<StudentInfoVo> getStudentInfoVoPage(Long current, Long size, StudentQueryVo queryVo) {
        Page<Student> page = new Page<>();
        Page<StudentInfoVo> voPage = new Page<>();
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        if (StringUtils.hasText(queryVo.getKeyword())){
            wrapper.like("student_id",queryVo.getKeyword())
                    .or()
                    .like("name",queryVo.getKeyword())
                    .or()
                    .like("remark",queryVo.getKeyword());
        }
        baseMapper.selectPage(page,wrapper);
        BeanUtils.copyProperties(page,voPage);
        List<StudentInfoVo> list = page.getRecords().stream().map(student -> {
            Major major = majorMapper.selectById(student.getMajorId());
            StudentInfoVo vo = new StudentInfoVo();
            BeanUtils.copyProperties(student, vo);
            vo.setCollege(collegeMapper.getNameById(major.getCollegeId()));
            vo.setMajor(major.getName());
            return vo;
        }).collect(Collectors.toList());
        voPage.setRecords(list);
        return voPage;
    }

    @Override
    public List<StudentScoreVo> getByCourse(Long id) {
        ArrayList<StudentScoreVo> list = new ArrayList<>();
        List<Score> scoreList = scoreMapper.selectList(new QueryWrapper<Score>().eq("course_id", id));
        scoreList.forEach(score -> {
            StudentScoreVo scoreVo = new StudentScoreVo();
            Student student = baseMapper.selectById(id);
            BeanUtils.copyProperties(student,scoreVo);
            scoreVo.setMajor(majorMapper.getNameByiId(student.getMajorId()));
            BeanUtils.copyProperties(score,scoreVo);
            list.add(scoreVo);
        });
        return list;
    }
}




