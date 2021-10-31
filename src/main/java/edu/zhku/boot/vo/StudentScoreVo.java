package edu.zhku.boot.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import edu.zhku.boot.converter.GenderConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author MJX
 * @date 2021/10/28
 */
@Data
public class StudentScoreVo {
    @ExcelProperty("学号")
    @ApiModelProperty("学号")
    private Long studentId;

    @ExcelProperty("姓名")
    @ApiModelProperty("姓名")
    private String name;

    @ExcelProperty("学院")
    @ApiModelProperty("学院")
    private String college;

    @ExcelProperty("专业")
    @ApiModelProperty("专业")
    private String major;

    @ExcelProperty(value = "性别",converter = GenderConverter.class)
    @ApiModelProperty("性别")
    private Integer gender;

    @ExcelProperty("平时成绩")
    private BigDecimal regularGrade;

    @ExcelProperty("期末成绩")
    private BigDecimal endtermGrade;

    @ExcelProperty("总成绩")
    private BigDecimal score;
}
