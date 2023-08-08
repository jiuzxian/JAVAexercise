//package com.example.springtest.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class MyFilterConfig {
//
//    @Resource
//    LoginFilter loginFilter;
//
//    @Bean
//    public FilterRegistrationBean registFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(loginFilter);
//        registration.addUrlPatterns("/*");
//        //需要排除的uri
//        registration.addInitParameter("excludedUris","/loginPost,/login");
//        registration.setName("LoginFilter");
//        registration.setOrder(1);
//        return registration;
//    }
//
//}
