package edu.zhku.boot.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import edu.zhku.boot.common.constant.RedisConstant;
import edu.zhku.boot.common.model.Result;
import edu.zhku.boot.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author MJX
 * @date 2021/10/28
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/get")
    public Result getCaptcha(){
        HashMap<String, String> map = new HashMap<>(2);
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(300, 100);
        String uuid = IdUtil.fastSimpleUUID();
        String redisKey = RedisConstant.CAPTCHA_KEY+ uuid;
        redisUtils.set(redisKey,captcha.getCode());
        String imageBase64 = captcha.getImageBase64();
        map.put("uuid",uuid);
        map.put("img",imageBase64);
        return Result.success(map);
    }
}
