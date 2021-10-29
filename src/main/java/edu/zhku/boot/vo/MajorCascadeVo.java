package edu.zhku.boot.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MJX
 * @date 2021/10/29
 */
@Data
public class MajorCascadeVo {
    private Long value;

    private String name;

    private List<MajorCascadeInfoVo> children;
}
