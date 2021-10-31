package edu.zhku.boot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.zhku.boot.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.vo.StudentCascadeVo;
import edu.zhku.boot.vo.StudentInfoVo;
import edu.zhku.boot.vo.StudentQueryVo;
import edu.zhku.boot.vo.StudentScoreVo;

import java.util.List;

/**
 *
 */
public interface StudentService extends IService<Student> {

    StudentInfoVo getStudentById(Long id);

    IPage<StudentInfoVo> getStudentInfoVoPage(Long current, Long size, StudentQueryVo queryVo);

    List<StudentScoreVo> getByCourse(Long id);

    List<StudentCascadeVo> getCascade();
}

