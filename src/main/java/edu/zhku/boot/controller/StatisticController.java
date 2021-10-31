package edu.zhku.boot.controller;

import com.alibaba.excel.EasyExcel;
import edu.zhku.boot.converter.GenderConverter;
import edu.zhku.boot.service.CourseService;
import edu.zhku.boot.service.StudentService;
import edu.zhku.boot.vo.StudentScoreVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Api("统计Api")
@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;
    @ApiOperation("导出课程学生信息Excel")
    @GetMapping("/getStudentInfoExcel/{courseId}")
    public void studentInfoExcel(@PathVariable Long courseId, HttpServletResponse response) throws IOException {
        List<StudentScoreVo> studentScores = studentService.getByCourse(courseId);
        String name = courseService.getById(courseId).getName();
        String fileName=name+"学生名单";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String s = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + s + ".xlsx");
        EasyExcel.write(response.getOutputStream(),StudentScoreVo.class)
                .registerConverter(new GenderConverter())
                .sheet("学生成绩")
                .doWrite(studentScores);
    }

}
