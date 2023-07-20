package com.example.springtest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
//定位网页
public class GetController {

    //登录页
    @GetMapping("/")
    public String toLogin(){
        return "Login";
    }

    //注销登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "Login";}


}
