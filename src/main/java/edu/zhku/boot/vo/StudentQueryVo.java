package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/26
 */
@Data
@ApiModel("学生信息查询Vo")
public class StudentQueryVo {
    private String keyword;

    private Integer majorId;
}
