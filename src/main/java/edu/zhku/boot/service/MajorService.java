package edu.zhku.boot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zhku.boot.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.vo.MajorCascadeVo;
import edu.zhku.boot.vo.MajorQueryVo;
import edu.zhku.boot.vo.MajorInfoVo;

import java.util.List;

/**
 *
 */
public interface MajorService extends IService<Major> {

    MajorInfoVo getMajorById(Long id);

    Page<MajorInfoVo> getMajorInfoVoPage(Long current, Long size, MajorQueryVo queryVo);

    List<MajorCascadeVo> getCascade();
}
