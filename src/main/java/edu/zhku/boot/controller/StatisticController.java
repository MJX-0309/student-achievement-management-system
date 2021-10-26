package edu.zhku.boot.controller;

import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.service.ScoreService;
import edu.zhku.boot.vo.CourseStatistVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Api("统计Api")
@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("courseStatistics/{courseId}")
    public Result courseStatistics(@PathVariable Long courseId){
        List<CourseStatistVo> list=scoreService.statist(courseId);
        return Result.success(list);
    }


}
