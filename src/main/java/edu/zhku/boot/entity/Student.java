package edu.zhku.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author MJX
 */
@TableName(value ="student")
@Data
@ApiModel("学生")
public class Student {

    @TableId
    @ApiModelProperty("学号")
    private Long studentId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("专业")
    private Long major;

    @ApiModelProperty("性别")
    private Byte gender;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("备注")
    private String remark;

}
