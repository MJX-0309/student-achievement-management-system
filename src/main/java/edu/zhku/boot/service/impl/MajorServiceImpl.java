package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.entity.Major;
import edu.zhku.boot.entity.MajorQueryVo;
import edu.zhku.boot.mapper.CollegeMapper;
import edu.zhku.boot.service.MajorService;
import edu.zhku.boot.mapper.MajorMapper;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.MajorInfoVo;
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
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
implements MajorService{

    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public MajorInfoVo getMajorById(Long id) {
        Major major = baseMapper.selectById(id);
        MajorInfoVo infoVo = new MajorInfoVo();
        BeanUtils.copyProperties(major,infoVo);
        infoVo.setCollege(collegeMapper.getNameById(id));
        return infoVo;
    }

    @Override
    public Page<MajorInfoVo> getMajorInfoVoPage(Long current, Long size, MajorQueryVo queryVo) {
        Page<Major> page = new Page<>(current,size);
        Page<MajorInfoVo> voPage = new Page<>();
        QueryWrapper<Major> wrapper = new QueryWrapper<>();

        baseMapper.selectPage(page,wrapper);
        BeanUtils.copyProperties(page,voPage);

        List<MajorInfoVo> list = page.getRecords().stream().map(major -> {
            MajorInfoVo infoVo = new MajorInfoVo();
            infoVo.setCollege(collegeMapper.getNameById(major.getMajorId()));
            return infoVo;
        }).collect(Collectors.toList());

        voPage.setRecords(list);
        return voPage;
    }
}




