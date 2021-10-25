package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Data
@ApiModel("课程查询Vo")
public class CourseQueryVo {
    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("开设学院id")
    private Long collegeId;

    @ApiModelProperty("课程类型")
    private Integer type;
}
