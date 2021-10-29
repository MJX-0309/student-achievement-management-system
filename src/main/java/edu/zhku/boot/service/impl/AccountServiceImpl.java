package edu.zhku.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zhku.boot.common.constant.RedisConstant;
import edu.zhku.boot.entity.Account;
import edu.zhku.boot.mapper.AccountMapper;
import edu.zhku.boot.service.AccountService;
import edu.zhku.boot.util.JWTUtils;
import edu.zhku.boot.util.RedisUtils;
import edu.zhku.boot.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author MJX
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
implements AccountService{

    @Autowired
    private RedisUtils redisUtils;




    @Override
    public String login(LoginVo loginVo) {
        boolean validateCaptcha = validateCaptcha(loginVo.getCode(), loginVo.getUuid());
        if(validateCaptcha){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Account select = baseMapper.selectById(loginVo.getTeacherId());
            if (select==null){
                throw new RuntimeException("用户不存在");
            }
            boolean matches = encoder.matches(loginVo.getPassword(), select.getPassword());
            if (matches) {
                return JWTUtils.createToken(Long.parseLong(loginVo.getTeacherId()));
            }else {
                throw new RuntimeException("密码不正确");
            }
        }else {
            throw new RuntimeException("验证码不正确");
        }
    }


    private boolean validateCaptcha( String code, String uuid) {
        String verifyKey = RedisConstant.CAPTCHA_KEY+ uuid;
        String captcha = redisUtils.get(verifyKey);
        redisUtils.delete(verifyKey);
        return captcha != null && captcha.equalsIgnoreCase(code);
    }
}




