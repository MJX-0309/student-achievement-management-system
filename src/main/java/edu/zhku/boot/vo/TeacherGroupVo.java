package edu.zhku.boot.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MJX
 * @date 2021/10/28
 */
@Data
public class TeacherGroupVo {
    private Long teacherId;
    private String name;
    private List<TeacherGroupInfo> teachers;
}
