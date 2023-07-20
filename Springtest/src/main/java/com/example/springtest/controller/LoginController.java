package com.example.springtest.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springtest.entity.User;
import com.example.springtest.mapper.UserMapper;
import com.example.springtest.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    //登录
    @PostMapping("/loginPost")
    public Map<String,Object> getCode(@RequestBody Map<String,Object> map, HttpSession session) throws Exception {
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        Map<String, Object> response = new HashMap<>();

//        LambdaQueryWrapper<User> userLambdaQueryWrapper=new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.eq(User::getAccount,account);
//        User user = userService.getOne(userLambdaQueryWrapper);

        User user= userMapper.findByAccount(account);


        if (user == null) {
            response.put("status", 0);
            response.put("msg", "非管理员账号！");
            return response;
        }
        if(!password.equals(user.getPassword())){
            response.put("status", 100);
            response.put("msg","密码错误!");
            return response;
        }
        response.put("status", 200);
        response.put("id",user.getId());
        session.setAttribute("user",user);
        return response;
    }

//    @GetMapping("/logout")
//    public String logout(HttpSession session, HttpServletResponse response) {
//        session.invalidate();
//        return "redirect:/login";
//    }







}
