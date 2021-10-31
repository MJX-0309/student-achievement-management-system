package edu.zhku.boot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

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

    public Score() {
    }

    public Score(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
