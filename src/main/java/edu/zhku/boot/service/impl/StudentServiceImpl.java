package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Student;
import edu.zhku.boot.mapper.MajorMapper;
import edu.zhku.boot.service.StudentService;
import edu.zhku.boot.mapper.StudentMapper;
import edu.zhku.boot.vo.StudentInfoVo;
import edu.zhku.boot.vo.StudentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public StudentInfoVo getStudentById(Long id) {
        Student student = baseMapper.selectById(id);
        StudentInfoVo infoVo = new StudentInfoVo();
        BeanUtils.copyProperties(student,infoVo);
        infoVo.setMajor(majorMapper.getNameByiId(student.getMajor()));
        infoVo.setGender(student.getGender()==1?"男":"女");
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
            StudentInfoVo vo = new StudentInfoVo();
            BeanUtils.copyProperties(student, vo);
            vo.setGender(student.getGender() == 1 ? "男" : "女");
            vo.setMajor(majorMapper.getNameByiId(student.getMajor()));
            return vo;
        }).collect(Collectors.toList());
        voPage.setRecords(list);
        return voPage;
    }
}




