package edu.zhku.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Major;
import edu.zhku.boot.entity.MajorQueryVo;
import edu.zhku.boot.service.MajorService;
import edu.zhku.boot.vo.MajorInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("新增")
    @PostMapping("/save")
    public Result save(@RequestBody Major major){
        majorService.save(major);
        return Result.success();
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result update(@RequestBody Major major){
        majorService.updateById(major);
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
                            @RequestBody(required = false) MajorQueryVo queryVo){
        Page<MajorInfoVo> voPage = majorService.getMajorInfoVoPage(current, size, queryVo);
        return Result.success(voPage);
    }
}
