package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Major;
import edu.zhku.boot.vo.MajorCascadeInfoVo;
import edu.zhku.boot.vo.MajorCascadeVo;
import edu.zhku.boot.vo.MajorQueryVo;
import edu.zhku.boot.service.MajorService;
import edu.zhku.boot.vo.MajorInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MJX
 * @date 2021/10/26
 */
@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @ApiOperation("通过id获取")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        MajorInfoVo major = majorService.getMajorById(id);
        return Result.success(major);
    }

    @ApiOperation("新增或更新")
    @PostMapping("/saveOrUpdate")
    public Result save(@RequestBody Major major){
        majorService.saveOrUpdate(major);
        return Result.success();
    }


    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id){
        majorService.removeById(id);
        return Result.success();
    }

    @ApiOperation("分页查询")
    @GetMapping("/page/{current}/{size}")
    public Result queryPage(@PathVariable Long current,
                            @PathVariable Long size,
                             MajorQueryVo queryVo){
        Page<MajorInfoVo> voPage = majorService.getMajorInfoVoPage(current, size, queryVo);
        return Result.success(voPage);
    }
    @ApiOperation("获取全部")
    @GetMapping("/getAll")
    public Result queryPage(){
        List<Major> list = majorService.list();
        return Result.success(list);
    }

    @ApiOperation("获取全部")
    @GetMapping("/getCascade")
    public Result getCascade(){
        List<MajorCascadeVo> list = majorService.getCascade();
        return Result.success(list);
    }
}
