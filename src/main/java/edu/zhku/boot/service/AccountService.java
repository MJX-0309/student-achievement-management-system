package edu.zhku.boot.service;

import edu.zhku.boot.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zhku.boot.vo.LoginVo;

/**
 *
 */
public interface AccountService extends IService<Account> {

    String login(LoginVo loginVo);

    void changePassword(Long teacherId, String oldPassword, String newPassword);
}
