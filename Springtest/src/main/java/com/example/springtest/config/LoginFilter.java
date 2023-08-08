package com.example.springtest.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springtest.util.TokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private TokenUtil tokenUtil;

    private String[] excludedUris;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String param = filterConfig.getInitParameter("excludedUris");
        if (StringUtils.isNotBlank(param)) {
            this.excludedUris = param.split(",");
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 定义表示变量 并验证用户请求URI 是否包含不过滤路径
        boolean flag = false;
        for (String uri:excludedUris) {
            if (request.getRequestURI().equals(uri)){
                flag = true;
            }
        }
        if(!flag){
            System.out.println("自定义过滤器filter,过滤url:"+request.getRequestURI());
            String token = request.getHeader("token");// 从 http 请求头中取出 token
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }

            // 验证 token 是否过期（有存），不在库里就回到登录页
            if (!redisTemplate.hasKey(token)){
                System.out.println("拦截重定向");
                response.sendRedirect("/login");
                return;
            }else {
                request.setAttribute("userId",tokenUtil.getId(token));
                tokenUtil.freshToken(token);
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
