package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Course;
import edu.zhku.boot.entity.CourseType;
import edu.zhku.boot.service.CourseService;
import edu.zhku.boot.service.CourseTypeService;
import edu.zhku.boot.service.SelectCourseService;
import edu.zhku.boot.vo.CourseInfoVo;
import edu.zhku.boot.vo.CourseQueryVo;
import edu.zhku.boot.vo.CourseRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private SelectCourseService selectCourseService;

    @ApiOperation("通过id获取")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        CourseInfoVo vo=courseService.getCourseById(id);
        return Result.success(vo);
    }

    @ApiOperation("新增/更新")
    @PostMapping("/saveOrUpdate")
    public Result save(@RequestBody CourseRequestVo course){
        courseService.saveCourse(course);
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
                            CourseQueryVo vo){
        IPage<CourseInfoVo> page=courseService.getCourseInfoVoPage(current,size,vo);
        return Result.success(page);
    }

    @ApiOperation("获取所有课程类型")
    @GetMapping("/getTypeList")
    public Result getTypeList(){
        List<CourseType> list = courseTypeService.list();
        return Result.success(list);
    }

}
