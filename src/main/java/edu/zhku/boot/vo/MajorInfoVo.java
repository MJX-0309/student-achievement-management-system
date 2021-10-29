package edu.zhku.boot.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Data
@ApiModel("专业Vo")
public class MajorInfoVo {

    @ApiModelProperty("专业编号")
    private Long majorId;

    @ApiModelProperty("专业名")
    private String name;

    @ApiModelProperty("所属学院")
    private Long collegeId;

    @ApiModelProperty("所属学院")
    private String collegeName;
}
