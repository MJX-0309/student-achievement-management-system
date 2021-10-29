package edu.zhku.boot.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String collegeId;

    @ApiModelProperty("学院名")
    private String collegeName;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("备注")
    private String remark;

}
