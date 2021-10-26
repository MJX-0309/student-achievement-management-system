package edu.zhku.boot.controller;

import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Score;
import edu.zhku.boot.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

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

    @ApiOperation("录入成绩")
    @PostMapping("/save")
    public Result save(@RequestBody Score score){
        scoreService.saveScore(score);
        return Result.success();
    }

    @ApiOperation("修改成绩")
    @PostMapping("/update")
    public Result update(@RequestBody Score score){
        scoreService.updateScore(score);
        return Result.success();
    }

    @ApiOperation("重置成绩")
    @PutMapping("/reset/{courseId}/{studentId}")
    public Result reset(@PathVariable Long courseId, @PathVariable Long studentId){
        scoreService.reset(courseId,studentId);
        return Result.success();
    }
}
