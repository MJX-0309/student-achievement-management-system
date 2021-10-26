package edu.zhku.boot.mapper;

import edu.zhku.boot.entity.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity edu.zhku.boot.entity.MajorInfoVo
 */
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    String getNameByiId(@Param("id") Long id);
}




