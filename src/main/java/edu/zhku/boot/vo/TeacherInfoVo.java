package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author MJX
 * @date 2021/10/25
 */
@Data
@ApiModel("教师信息Vo")
public class TeacherInfoVo {

    @ApiModelProperty("教师id")
    private Long teacherId;

    @ApiModelProperty("学院id")
    private Long collegeId;

    @ApiModelProperty("学院名")
    private String collegeName;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("备注")
    private String remark;

}
