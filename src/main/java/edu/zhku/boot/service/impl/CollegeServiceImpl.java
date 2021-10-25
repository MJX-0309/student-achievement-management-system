package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.College;
import edu.zhku.boot.service.CollegeService;
import edu.zhku.boot.mapper.CollegeMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
implements CollegeService{

}




