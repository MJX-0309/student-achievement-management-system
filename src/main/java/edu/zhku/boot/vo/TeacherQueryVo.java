package edu.zhku.boot.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/25
 */
@ApiModel("教师信息查询")
@Data
public class TeacherQueryVo {

    private String keyword;

    private Long collegeId;

}
