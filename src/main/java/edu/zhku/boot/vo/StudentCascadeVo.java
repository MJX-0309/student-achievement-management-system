package edu.zhku.boot.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MJX
 * @date 2021/10/31
 */
@Data
public class StudentCascadeVo {
    private Long value;

    private String name;

    private List<StudentCascadeVo> children;

    private boolean leaf;
}
