package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Student;
import edu.zhku.boot.service.StudentService;
import edu.zhku.boot.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
implements StudentService{

}




