package com.example.springtest.annotation;

import com.example.springtest.entity.Log;
import com.example.springtest.entity.Result;
import com.example.springtest.service.LogService;
import com.example.springtest.util.HttpContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * aop日志切面处理类
 */

@Aspect
@Component
public class AopLoggerAspect {
    @Resource
    LogService logService;

    // 定义一个切点，用于匹配那些被 @AopLogger 注解标记的方法。
    @Pointcut("@annotation(com.example.springtest.annotation.AopLogger)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        // AOP框架需要通过 @Around 方法的返回值来确定目标方法是否继续执行，以及传递给外部的执行结果。所以@Around 方法必须返回一个 Object 类型的值。
        Object result = point.proceed();
        Timestamp time=new Timestamp(System.currentTimeMillis());
        // 保存日志
        logSave(point,time,result);

        return result; // 返回目标方法的执行结果
    }

    /**
     * 保存日志方法
     * @param point
     * @param time
     */
    public void logSave(ProceedingJoinPoint point, Timestamp time,Object result){

        Log log=new Log();

        //获得方法签名，为了拿到方法名
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();
        String className = point.getTarget().getClass().getName();
        log.setMethod(className + "." + methodName + "()");


        //拿到注解实例，为了得到定义的value
        Method method = signature.getMethod();
        AopLogger aopLogger=method.getAnnotation(AopLogger.class);
        if(aopLogger!=null){
            log.setType(aopLogger.value());
        }

        //拿到请求传入的参数
        Object[] args = point.getArgs();
        try{
            String object = Arrays.toString(args);
            log.setObject(object);
        }catch (Exception e){
        }

        //获取request,拿到操作人
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        log.setUserId(Integer.valueOf(String.valueOf(request.getAttribute("userId"))));

        //执行时间
        log.setOperateAt(time);

        //拿到方法返回结果是否成功
        Result result1=(Result) result;
        if(result1.getStatus()==200){
            log.setSuccessful(1);
        }else {
            log.setSuccessful(0);
        }

        logService.save(log);

    }


}
