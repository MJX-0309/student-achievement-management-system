package edu.zhku.boot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zhku.boot.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.vo.TeacherGroupVo;
import edu.zhku.boot.vo.TeacherInfoVo;
import edu.zhku.boot.vo.TeacherQueryVo;

import java.util.List;

/**
 *
 */
public interface TeacherService extends IService<Teacher> {

    TeacherInfoVo getTeacherById(Long id);

    Page<TeacherInfoVo> getTeacherInfoVoPage(Long current, Long size, TeacherQueryVo queryVo);

    List<TeacherGroupVo> getGroupByCollege();
}
