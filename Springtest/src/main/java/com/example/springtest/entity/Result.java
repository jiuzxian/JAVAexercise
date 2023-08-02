package com.example.springtest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Result<T> {
    //TODO 不要用基础类型
    private Integer status;
    private String msg;
    private T data;


    public static Result success(){
        return new Result(200,"操作成功!",null);
    }
    public static Result success(String msg){
        return new Result(200,msg,null);
    }
    public static <T> Result<T> success(T t){
        return new Result(200,"操作成功!",t);
    }
    public static <T> Result<T> success(int status, String msg, T t){
        return new Result(status,msg,t);
    }


    //101 库中没有相应数据
    //102 前端传来空数据
    public static Result fail(){
        return new Result(100,"操作失败!",null);
    }
    public static Result fail(int status,String msg){
        return new Result(status,msg,null);
    }
    public static <T> Result<T> fail(T t){
        return new Result(100,"操作失败!",t);
    }
    public static <T> Result<T> fail(int status, String msg, T t){
        return new Result(status,msg,t);
    }
    





}
