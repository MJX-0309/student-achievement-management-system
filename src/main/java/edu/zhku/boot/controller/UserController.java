package edu.zhku.boot.controller;

import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Account;
import org.springframework.web.bind.annotation.*;

/**
 * @author MJX
 * @date 2021/10/27
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Result login(){
        return Result.success("123","123");
    }

    @PostMapping("changePassword")
    public Result changePassword(Account account){
        return Result.success();
    }

    @GetMapping("/info")
    public Result getUserInfo(String token){
        return Result.success("123");
    }
}
