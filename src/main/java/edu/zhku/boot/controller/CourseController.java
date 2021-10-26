package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.entity.Student;
import edu.zhku.boot.service.CourseService;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.CourseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Api("课程管理Api")
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("通过id获取")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        CourseInfoVo vo=courseService.getCourseById(id);
        return Result.success(vo);
    }

    @ApiOperation("新增")
    @PostMapping("/save")
    public Result save(@RequestBody Course course){
        courseService.save(course);
        return Result.success();
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result update(@RequestBody Course course){
        courseService.updateById(course);
        return Result.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        courseService.removeById(id);
        return Result.success();
    }

    @ApiOperation("分页查询")
    @GetMapping("page/{current}/{size}")
    public Result queryPage(@PathVariable Long current,
                            @PathVariable Long size,
                            @RequestBody(required = false)CourseQueryVo vo){
        IPage<CourseInfoVo> page=courseService.getCourseInfoVoPage(current,size,vo);
        return Result.success(page);
    }
}
