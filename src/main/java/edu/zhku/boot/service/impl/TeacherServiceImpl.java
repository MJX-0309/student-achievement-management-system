package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Teacher;
import edu.zhku.boot.service.TeacherService;
import edu.zhku.boot.mapper.TeacherMapper;
import edu.zhku.boot.vo.TeacherInfoVo;
import edu.zhku.boot.vo.TeacherQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
implements TeacherService{

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public TeacherInfoVo getTeacherById(Long id) {
        Teacher teacher = baseMapper.selectById(id);
        TeacherInfoVo vo = new TeacherInfoVo();
        BeanUtils.copyProperties(teacher,vo);
        vo.setCollege(teacherMapper.getNameById(teacher.getTeacherId()));
        vo.setGender(teacher.getGender()==1?"男":"女");
        return vo;
    }

    public Page<TeacherInfoVo> getTeacherInfoVoPage(Long current, Long size, TeacherQueryVo queryVo) {
        Page<Teacher> page = new Page<>(current, size);
        Page<TeacherInfoVo> voPage = new Page<>(current, size);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(queryVo.getKeyword())){
            wrapper.like("name", queryVo.getKeyword())
                    .or()
                    .like("remark", queryVo.getKeyword());
        }
        if (queryVo.getCollege()!=null){
            wrapper.eq("college_id", queryVo.getCollege());
        }
        List<TeacherInfoVo> collect = baseMapper.selectPage(page, wrapper).getRecords().stream().map(teacher -> {
            TeacherInfoVo vo = new TeacherInfoVo();
            return vo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(page,voPage);
        voPage.setRecords(collect);
        return voPage;
    }
}




