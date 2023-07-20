package com.example.springtest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    //拦截器应用
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new com.example.springtest.config.LoginInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/loginPost");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}



