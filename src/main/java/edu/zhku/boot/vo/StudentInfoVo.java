package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Data
@ApiModel("学生信息Vo")
public class StudentInfoVo {

    @ApiModelProperty("学号")
    private Long studentId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("学院")
    private String college;

    @ApiModelProperty("专业")
    private String major;

    @ApiModelProperty("专业")
    private Long majorId;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("备注")
    private String remark;
}
