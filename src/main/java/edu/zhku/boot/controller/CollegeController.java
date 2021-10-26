package edu.zhku.boot.controller;

import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.College;
import edu.zhku.boot.service.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MJX
 * @date 2021/10/27
 */
@Api("学院信息Api")
@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @ApiOperation("获取所有学院")
    @GetMapping("/getCollegeList")
    public Result getCollegeList(){
        List<College> list = collegeService.list();
        return Result.success(list);
    }
}
