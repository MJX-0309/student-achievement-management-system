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
 */
@TableName(value ="college")
@Data
@ApiModel("学院")
public class College {

    @TableId
    @ApiModelProperty("学院编号")
    private Long collegeId;

    @ApiModelProperty("学院名称")
    private String name;

}
