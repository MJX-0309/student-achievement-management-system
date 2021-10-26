package edu.zhku.boot.mapper;

import edu.zhku.boot.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity edu.zhku.boot.entity.Score
 */
public interface ScoreMapper extends BaseMapper<Score> {

    void reset(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}




