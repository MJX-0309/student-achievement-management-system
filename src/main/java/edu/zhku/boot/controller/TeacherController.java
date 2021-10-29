package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Account;
import edu.zhku.boot.entity.Teacher;
import edu.zhku.boot.service.AccountService;
import edu.zhku.boot.service.TeacherService;
import edu.zhku.boot.vo.TeacherGroupVo;
import edu.zhku.boot.vo.TeacherInfoVo;
import edu.zhku.boot.vo.TeacherQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author MJX
 * @date 2021/10/25
 */
@Slf4j
@Api("教师管理Api")
@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ThreadPoolExecutor executor;
    @ApiOperation("通过id获取")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        TeacherInfoVo teacher = teacherService.getTeacherById(id);
        return Result.success(teacher);
    }

    @ApiOperation("新增/更新")
    @PostMapping("/saveOrUpdate")
    public Result save(@RequestBody Teacher teacher){
        teacherService.saveOrUpdate(teacher);
        long count = accountService.count(new QueryWrapper<Account>().eq("id", teacher.getTeacherId()));
        if (count==0){
            CompletableFuture.runAsync(()->{
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                accountService.saveOrUpdate(new Account(teacher.getTeacherId(),encoder.encode("123456"),1));
            },executor);
        }
        return Result.success();
    }


    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id){
        teacherService.removeById(id);
        return Result.success();
    }

    @ApiOperation("分页查询")
    @GetMapping("/page/{current}/{size}")
    public Result queryPage(@PathVariable Long current,
                            @PathVariable Long size,
                             TeacherQueryVo queryVo){
        Page<TeacherInfoVo> voPage = teacherService.getTeacherInfoVoPage(current, size, queryVo);
        return Result.success(voPage);
    }

    @ApiOperation("按学院分组")
    @GetMapping("/group")
    public Result getGroupByCollege(){
        List<TeacherGroupVo> vo=teacherService.getGroupByCollege();
        return Result.success(vo);
    }
}
