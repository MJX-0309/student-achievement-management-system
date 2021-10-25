package edu.zhku.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author MJX
 */
@TableName(value ="course")
@Data
public class Course {

    @TableId
    @ApiModelProperty("课程编号")
    private Long courseId;

    @ApiModelProperty("课程名")
    private String name;


    @ApiModelProperty("开设学院id")
    private Long collegeId;

    @ApiModelProperty("课程类型")
    private Byte type;

    @ApiModelProperty("理论学时")
    private Integer theoreticalHours;

    @ApiModelProperty("实践学时")
    private Integer practicalHours;

    @ApiModelProperty("平时比例")
    private BigDecimal regularRatio;

    @ApiModelProperty("期末比例")
    private BigDecimal endtermRatio;

    @ApiModelProperty("学分")
    private Integer credit;

}
