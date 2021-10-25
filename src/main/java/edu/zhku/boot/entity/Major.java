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
@TableName(value ="major")
@Data
@ApiModel("专业")
public class Major {

    @TableId
    @ApiModelProperty("专业编号")
    private Long majorId;

    @ApiModelProperty("专业名")
    private String name;

    @ApiModelProperty("所属学院")
    private Long college;
}
