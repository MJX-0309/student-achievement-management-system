package edu.zhku.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author MJX
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
public class Teacher {

    @TableId
    @ApiModelProperty("教师id")
    private Long teacherId;

    @ApiModelProperty("学院id")
    private Long collegeId;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("备注")
    private String remark;
}
