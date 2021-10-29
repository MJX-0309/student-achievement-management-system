package edu.zhku.boot.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import edu.zhku.boot.entity.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Data
@ApiModel("课程信息Vo")
public class CourseInfoVo {
    @TableId
    @ApiModelProperty("课程编号")
    private Long courseId;

    @ApiModelProperty("课程名")
    private String name;

    @ApiModelProperty("开设学院")
    private Long collegeId;

    @ApiModelProperty("开设学院名字")
    private String collegeName;

    @ApiModelProperty("课程类型")
    private String type;

    @ApiModelProperty("理论学时")
    private Integer theoreticalHours;

    @ApiModelProperty("实践学时")
    private Integer practicalHours;

    @ApiModelProperty("平时比例")
    private Integer regularRatio;

    @ApiModelProperty("期末比例")
    private Integer endtermRatio;

    @ApiModelProperty("管理教师信息")
    private Teacher managerTeacher;

    @ApiModelProperty
    private List<Teacher> teachers;

    @ApiModelProperty("学分")
    private BigDecimal credit;
}
