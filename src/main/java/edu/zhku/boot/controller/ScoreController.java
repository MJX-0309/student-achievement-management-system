package edu.zhku.boot.controller;

import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Score;
import edu.zhku.boot.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Api("成绩管理Api")
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    @ApiOperation("录入成绩/修改成绩")
    @PostMapping("/saveOrUpdate")
    public Result save(@RequestBody Score score){
        scoreService.saveScore(score);
        return Result.success();
    }

    @ApiOperation("重置成绩")
    @PutMapping("/reset/{courseId}/{studentId}")
    public Result reset(@PathVariable Long courseId, @PathVariable Long studentId){
        scoreService.reset(courseId,studentId);
        return Result.success();
    }

    @ApiOperation("添加学生")
    @PostMapping("/addStudents/{courseId}")
    public Result addStudents(@PathVariable Long courseId,@RequestBody List<Long> studentIds ){
        for (Long studentId : studentIds) {
            Score score = new Score(courseId, studentId);
            try {
                scoreService.save(score);
            } catch (Exception e) {
                throw new RuntimeException("出现重复学生");
            }
        }
        return Result.success();
    }
}
