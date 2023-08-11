package com.example.springtest.exception;

/**
 * 不在数据库中异常100
 */
public class NotInException extends RuntimeException {
    private final int code=100;
    public NotInException(String s) {
        super(s);
    }

    public int getCode() {
        return code;
    }
}