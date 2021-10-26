package edu.zhku.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author MJX
 * @TableName select_course
 */
@TableName(value ="select_course")
@Data
@ApiModel("选课")
public class SelectCourse {

    @ApiModelProperty("教师id")
    private Long teacherId;

    @ApiModelProperty("课程id")
    private Long courseId;

    @ApiModelProperty("可给成绩")
    private Integer isAuthorized=1;

}
