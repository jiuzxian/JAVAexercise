package com.example.springtest.config;

import com.example.springtest.util.JWTUtil;
import com.example.springtest.util.TokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private TokenUtil tokenUtil;

//    Springutil.getBean
// 2.
// RedisTemplate redisTemplate= (RedisTemplate) SpringUtil.getBean("redisTemplate");

// 2.
//    ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
//    RedisTemplate redisTemplate=context.getBean(RedisTemplate.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }

        // 验证 token 是否过期（有存），不在库里就回到登录页
        //TODO jwt + redis的方案没有写？
        if (!redisTemplate.hasKey(token)){
            httpServletResponse.sendRedirect("/login");//.sendRedirect(httpServletRequest.getContextPath()+"/login");//
            return false;
        }else {
            httpServletRequest.setAttribute("userId",tokenUtil.getId(token));
            tokenUtil.freshToken(token);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}

//(有状态登录)当用户未登录进入任何页面时，一律跳转到登录界面
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(request.getSession().getAttribute("user") == null)
//        {
//            response.sendRedirect("/login");
//            return false;
//        }
//        return true;
//    }}





