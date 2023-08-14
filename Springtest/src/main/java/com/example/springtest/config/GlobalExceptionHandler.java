package com.example.springtest.config;


import com.example.springtest.entity.Result;
import com.example.springtest.exception.InNullException;
import com.example.springtest.exception.NotInException;
import com.example.springtest.exception.NotMatchException;
import com.example.springtest.exception.ServletResponseEnum;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotInException.class)
    public Result handleNotInException(NotInException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotMatchException.class)
    public Result handleNotMatchException(NotMatchException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(InNullException.class)
    public Result handleInNullException(InNullException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }


    //controller异常
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Result handleServletException(Exception e) {

        int status = 500; //默认
        String message = "服务器内部错误"; //默认
        try {
            //TODO 要对InNullException做特殊处理,怎么写
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            status = servletExceptionEnum.getCode();
            message = servletExceptionEnum.getMessage();
        } catch (IllegalArgumentException e1) {
            //TODO 用日志组件进行记录 slf4j + logback
            System.out.println("class [" + e.getClass().getName() + "] not defined in enum " + ServletResponseEnum.class.getName());
        }
        return Result.fail(status, message);
    }



}
