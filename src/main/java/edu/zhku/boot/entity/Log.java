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
 * @TableName log
 */
@TableName(value ="log")
@Data
@ApiModel("日志")
public class Log {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;
    
    @ApiModelProperty("操作人id")
    private Long operatorId;
    
    @ApiModelProperty("操作类型")
    private String type;

    @ApiModelProperty("ip地址")
    private String ipAddress;

    @ApiModelProperty("操作时间")
    private Date createTime;
}
