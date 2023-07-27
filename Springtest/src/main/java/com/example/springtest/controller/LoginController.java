package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.Result;
import com.example.springtest.entity.User;
import com.example.springtest.mapper.UserMapper;
import com.example.springtest.service.TokenService;
import com.example.springtest.service.UserService;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    //登录
    @PostMapping("/loginPost")
    public Result getCode(@RequestParam("account")String account, @RequestParam("password")String password,HttpSession session) throws Exception {

        User user = userService.findByAccount(account);

        if (ObjectUtils.isEmpty(user)) {
            return Result.fail();
        }
        if (!password.equals(user.getPassword())) {
            return Result.fail(101, "密码错误！");
        }
        session.setAttribute("user", user);

        String token=tokenService.freshToken(String.valueOf(user.getId()));

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userId",user.getId());
        result.put("token", token);

        return Result.success(result);

    }


}
