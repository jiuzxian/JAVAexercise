package com.example.springtest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {
//    //拦截器应用
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
            //TODO 要使用springboot的自动注入，不能使用new 来创建对象
//        registry.addInterceptor(new com.example.springtest.config.LoginInterceptor())
//                .addPathPatterns("/**").excludePathPatterns("/loginPost","/login");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }

    @Resource
    private LoginInterceptor loginInterceptor;

    //拦截器应用
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/loginPost","/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}



