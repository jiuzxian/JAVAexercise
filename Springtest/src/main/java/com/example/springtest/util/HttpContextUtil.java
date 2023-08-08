package com.example.springtest.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 此类从当前线程中获取 HttpServletRequest 对象，是因为它使用了 Spring 框架提供的 RequestContextHolder 类。
 * RequestContextHolder 是一个用于存储请求上下文的容器，它使用了线程局部变量（ThreadLocal）来确保每个线程都可以访问自己的请求上下文。
 * 在多线程环境中，每个线程都有自己的局部变量副本，这使得每个线程都可以独立地存储和访问数据，而不会互相干扰。
 * RequestContextHolder 利用了这一点，将请求上下文对象存储在当前线程的局部变量中，以便在整个线程执行期间都可以访问。
 */

public class HttpContextUtil {

    // 获取当前的 HttpServletRequest 对象
    public static HttpServletRequest getHttpServletRequest() {
        // 通过 RequestContextHolder 获取当前线程的请求上下文，再从中获取 HttpServletRequest 对象
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    // 获取当前请求的域名部分，不包括路径和查询参数
    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        // 获取请求的 URL
        StringBuffer url = request.getRequestURL();
        // 删除 URL 中的 URI 部分，只保留域名部分，并将其转换为字符串
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    // 获取当前请求的来源信息，通常用于跨域请求
    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        // 从 HTTP 头中获取 "Origin" 标头的值，表示请求的来源
        return request.getHeader("Origin");
    }
}

