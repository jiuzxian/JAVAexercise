package com.example.springtest.config;

import com.example.springtest.service.EmployeesService;
import com.example.springtest.service.TokenService;
import com.example.springtest.util.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor implements HandlerInterceptor {


////    Springutil.geiBean
//
//    RedisTemplate redisTemplate= (RedisTemplate) SpringUtil.getBean("redisTemplate");
//
////    @Resource
////    RedisTemplate redisTemplate;
//
//
//        @Override
//        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
//            String userId= httpServletRequest.getHeader("userId");
//
//
//
//            if (token == null) {
//                throw new RuntimeException("无token，请重新登录");
//            }
//            // 验证 token 是否有效
//            if(!token.equals(redisTemplate.opsForValue().get(userId))){
//                throw new RuntimeException("token已失效，请重新登录");
//            }
//
//            return true;
//        }
//
//        @Override
//        public void postHandle(HttpServletRequest httpServletRequest,
//                               HttpServletResponse httpServletResponse,
//                               Object o, ModelAndView modelAndView) throws Exception {
//
//        }
//
//        @Override
//        public void afterCompletion(HttpServletRequest httpServletRequest,
//                                    HttpServletResponse httpServletResponse,
//                                    Object o, Exception e) throws Exception {
//        }
//    }

//(有状态登录)当用户未登录进入任何页面时，一律跳转到登录界面
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null)
        {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }}





