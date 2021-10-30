package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author MJX
 * @date 2021/10/28
 */
@Data
public class StudentScoreVo {
    @ApiModelProperty("学号")
    private Long studentId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("学院")
    private String college;

    @ApiModelProperty("专业")
    private String major;

    @ApiModelProperty("性别")
    private Integer gender;

    private BigDecimal regularGrade;
    private BigDecimal endtermGrade;
    private BigDecimal score;
}
