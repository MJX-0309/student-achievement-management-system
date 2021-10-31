package edu.zhku.boot.controller;

import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.entity.Account;
import edu.zhku.boot.service.AccountService;
import edu.zhku.boot.service.TeacherService;
import edu.zhku.boot.util.JWTUtils;
import edu.zhku.boot.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author MJX
 * @date 2021/10/27
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TeacherService teacherService;
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        String token=accountService.login(loginVo);
        return Result.success(token);
    }

    @PostMapping("/changePassword")
    public Result changePassword(Account account){
        return Result.success();
    }

    @GetMapping("/info")
    public Result getUserInfo(String token){
        HashMap<String, Object> map = new HashMap<>(3);
        Long teacherId = JWTUtils.getUserId(token);

        String name = teacherService.getById(teacherId).getName();
        Account account = accountService.getById(teacherId);
        map.put("teacherId",teacherId);
        map.put("name",name);
        ArrayList<String> roles = new ArrayList<>();
        roles.add(account.getRole()==0?"ADMIN":"TEACHER");
        map.put("roles",roles);
        return Result.success(map);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.success();
    }
}
