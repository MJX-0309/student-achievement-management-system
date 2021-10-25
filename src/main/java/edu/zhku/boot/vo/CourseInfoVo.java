package edu.zhku.boot.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

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
    private String college;

    @ApiModelProperty("课程类型")
    private String type;

    @ApiModelProperty("理论学时")
    private Integer theoreticalHours;

    @ApiModelProperty("实践学时")
    private Integer practicalHours;

    @ApiModelProperty("平时比例")
    private BigDecimal regularRatio;

    @ApiModelProperty("期末比例")
    private BigDecimal endtermRatio;

    @ApiModelProperty("学分")
    private BigDecimal credit;
}
