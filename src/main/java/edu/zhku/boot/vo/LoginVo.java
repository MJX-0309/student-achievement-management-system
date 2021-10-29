package edu.zhku.boot.vo;

import lombok.Data;

/**
 * @author MJX
 * @date 2021/10/28
 */
@Data
public class LoginVo {
    /**
     * 用户名
     */
    private Long teacherId;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";
}
