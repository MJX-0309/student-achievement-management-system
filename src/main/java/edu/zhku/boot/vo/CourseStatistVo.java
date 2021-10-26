package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/26
 */
@AllArgsConstructor
@Data
@ApiModel("课程成绩统计")
public class CourseStatistVo {
    @ApiModelProperty("成绩范围")
    private String scope;

    @ApiModelProperty("人数")
    private Integer count;
}
