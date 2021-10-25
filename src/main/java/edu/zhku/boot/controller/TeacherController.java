package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Teacher;
import edu.zhku.boot.service.TeacherService;
import edu.zhku.boot.vo.TeacherInfoVo;
import edu.zhku.boot.vo.TeacherQueryVo;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MJX
 * @date 2021/10/25
 */
@Api("教师管理Api")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        TeacherInfoVo teacher = teacherService.getTeacherById(id);
        return Result.success(teacher);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Teacher teacher){
        teacherService.updateById(teacher);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id){
        teacherService.removeById(id);
        return Result.success();
    }

    @GetMapping("/page/{current}/{size}")
    public Result queryPage(@PathVariable Long current,
                            @PathVariable Long size,
                            @RequestBody(required = false) TeacherQueryVo queryVo){
        Page<TeacherInfoVo> voPage = teacherService.getTeacherInfoVoPage(current, size, queryVo);
        return Result.success(voPage);
    }


}
