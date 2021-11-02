package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.College;
import edu.zhku.boot.entity.Teacher;
import edu.zhku.boot.mapper.CollegeMapper;
import edu.zhku.boot.service.TeacherService;
import edu.zhku.boot.mapper.TeacherMapper;
import edu.zhku.boot.vo.TeacherGroupInfo;
import edu.zhku.boot.vo.TeacherGroupVo;
import edu.zhku.boot.vo.TeacherInfoVo;
import edu.zhku.boot.vo.TeacherQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public TeacherInfoVo getTeacherById(Long id) {
        Teacher teacher = baseMapper.selectById(id);
        TeacherInfoVo vo = new TeacherInfoVo();
        BeanUtils.copyProperties(teacher, vo);
        vo.setCollegeName(collegeMapper.getNameById(teacher.getTeacherId()));
        return vo;
    }

    @Override
    public Page<TeacherInfoVo> getTeacherInfoVoPage(Long current, Long size, TeacherQueryVo queryVo) {
        Page<Teacher> page = new Page<>(current, size);
        Page<TeacherInfoVo> voPage = new Page<>(current, size);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (queryVo.getCollegeId() != null) {
            wrapper.eq("college_id", queryVo.getCollegeId());
        }
        if (StringUtils.hasText(queryVo.getKeyword())) {
            wrapper.and(wrapper2 -> {
                wrapper2.like("name", queryVo.getKeyword())
                        .or()
                        .like("remark", queryVo.getKeyword());
            });
        }

        List<TeacherInfoVo> collect = baseMapper.selectPage(page, wrapper).getRecords().stream().map(teacher -> {
            TeacherInfoVo vo = new TeacherInfoVo();
            BeanUtils.copyProperties(teacher, vo);
            vo.setCollegeName(collegeMapper.getNameById(teacher.getCollegeId()));
            return vo;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(page, voPage);
        voPage.setRecords(collect);
        return voPage;
    }

    @Override
    public List<TeacherGroupVo> getGroupByCollege() {
        ArrayList<TeacherGroupVo> list = new ArrayList<>();
        List<College> collegeList = collegeMapper.selectList(null);
        collegeList.forEach(college -> {
            TeacherGroupVo groupVo = new TeacherGroupVo();
            groupVo.setTeacherId(college.getCollegeId());
            groupVo.setName(college.getName());
            List<TeacherGroupInfo> teacherGroupInfos = baseMapper.selectList(new QueryWrapper<Teacher>().eq("college_id", college.getCollegeId())).stream().map(teacher -> {
                TeacherGroupInfo info = new TeacherGroupInfo();
                BeanUtils.copyProperties(teacher, info);
                return info;
            }).collect(Collectors.toList());
            groupVo.setTeachers(teacherGroupInfos);
            list.add(groupVo);
        });
        return list;
    }
}




