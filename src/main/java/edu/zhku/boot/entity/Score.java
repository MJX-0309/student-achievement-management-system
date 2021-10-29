package edu.zhku.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author MJX
 */
@TableName(value ="score")
@Data
@ApiModel("成绩")
public class Score{


    @ApiModelProperty("课程id")
    private Long courseId;

    @ApiModelProperty("学生id")
    private Long studentId;

    @ApiModelProperty("平时成绩")
    private BigDecimal regularGrade;

    @ApiModelProperty("期末成绩")
    private BigDecimal endtermGrade;

    @ApiModelProperty("成绩")
    private BigDecimal score;
}
