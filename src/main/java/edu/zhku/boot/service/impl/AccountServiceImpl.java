package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.entity.Account;
import edu.zhku.boot.service.AccountService;
import edu.zhku.boot.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
implements AccountService{

}




