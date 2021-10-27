package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/26
 */
@ApiModel("专业查询Vo")
@Data
public class MajorQueryVo {
    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("学院")
    private Long college;
}
