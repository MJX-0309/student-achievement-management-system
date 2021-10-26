package edu.zhku.boot.service;

import edu.zhku.boot.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.vo.CourseStatistVo;

import java.util.List;

/**
 *
 */
public interface ScoreService extends IService<Score> {

    void saveScore(Score score);

    void updateScore(Score score);

    void reset(Long courseId, Long studentId);

    List<CourseStatistVo> statist(Long courseId);
}
