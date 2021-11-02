package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.College;
import edu.zhku.boot.entity.Major;
import edu.zhku.boot.vo.MajorCascadeInfoVo;
import edu.zhku.boot.vo.MajorCascadeVo;
import edu.zhku.boot.vo.MajorQueryVo;
import edu.zhku.boot.mapper.CollegeMapper;
import edu.zhku.boot.service.MajorService;
import edu.zhku.boot.mapper.MajorMapper;
import edu.zhku.boot.vo.MajorInfoVo;
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
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
implements MajorService{

    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public MajorInfoVo getMajorById(Long id) {
        Major major = baseMapper.selectById(id);
        MajorInfoVo infoVo = new MajorInfoVo();
        BeanUtils.copyProperties(major,infoVo);
        infoVo.setCollegeName(collegeMapper.getNameById(major.getCollegeId()));
        infoVo.setCollegeId(major.getCollegeId());
        return infoVo;
    }

    @Override
    public Page<MajorInfoVo> getMajorInfoVoPage(Long current, Long size, MajorQueryVo queryVo) {
        Page<Major> page = new Page<>(current,size);
        Page<MajorInfoVo> voPage = new Page<>();
        QueryWrapper<Major> wrapper = new QueryWrapper<>();
        if (queryVo.getCollegeId()!=null){
            wrapper.eq("college_id",queryVo.getCollegeId());
        }
        if (StringUtils.hasText(queryVo.getKeyword())){
            wrapper.and(majorQueryWrapper -> majorQueryWrapper.like("major_id",queryVo.getKeyword())
                    .or()
                    .like("name",queryVo.getKeyword()));
        }
        baseMapper.selectPage(page,wrapper);
        BeanUtils.copyProperties(page,voPage);

        List<MajorInfoVo> list = page.getRecords().stream().map(major -> {
            MajorInfoVo infoVo = new MajorInfoVo();
            BeanUtils.copyProperties(major,infoVo);
            infoVo.setCollegeName(collegeMapper.getNameById(major.getCollegeId()));
            infoVo.setCollegeId(major.getCollegeId());
            return infoVo;
        }).collect(Collectors.toList());

        voPage.setRecords(list);
        return voPage;
    }

    @Override
    public List<MajorCascadeVo> getCascade() {
        ArrayList<MajorCascadeVo> list = new ArrayList<>();

        List<College> colleges = collegeMapper.selectList(null);
        colleges.forEach(college -> {
            MajorCascadeVo cascadeVo = new MajorCascadeVo();
            cascadeVo.setName(college.getName());
            cascadeVo.setValue(college.getCollegeId());
            List<Major> majors = baseMapper.selectList(new QueryWrapper<Major>().eq("college_id", college.getCollegeId()));
            List<MajorCascadeInfoVo> collect = majors.stream().map(major -> {
                MajorCascadeInfoVo infoVo = new MajorCascadeInfoVo();
                infoVo.setValue(major.getMajorId());
                infoVo.setName(major.getName());
                return infoVo;
            }).collect(Collectors.toList());
            cascadeVo.setChildren(collect);
            list.add(cascadeVo);
        });
        return list;
    }
}




