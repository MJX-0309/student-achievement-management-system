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
 * @TableName course_type
 */
@TableName(value ="course_type")
@Data
@ApiModel("课程类型")
public class CourseType {

    @TableId
    @ApiModelProperty("类型编号")
    private Integer typeId;

    @ApiModelProperty("类型名")
    private String name;
}
