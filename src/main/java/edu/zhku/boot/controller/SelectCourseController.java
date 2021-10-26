package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.SelectCourse;
import edu.zhku.boot.service.SelectCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Api("教师选课Api")
@RestController
@RequestMapping("/select")
public class SelectCourseController {

    @Autowired
    private SelectCourseService selectCourseService;

    @ApiOperation("选课")
    @PostMapping("/save/{teacherId}/{courseId}")
    public Result save(@PathVariable Long teacherId, @PathVariable Long courseId){
        selectCourseService.save(new SelectCourse(teacherId,courseId,1));
        return Result.success();
    }

    @ApiOperation("退课")
    @PostMapping("/back/{teacherId}/{courseId}")
    public Result delete(@PathVariable Long teacherId, @PathVariable Long courseId){
        selectCourseService.remove(new QueryWrapper<SelectCourse>().eq("teacher_id",teacherId)
                .and(wrapper->wrapper.eq("course_id",courseId)));
        return Result.success();
    }
}
