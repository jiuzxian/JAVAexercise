package com.example.springtest.exception;

/**
 * 传入为空异常102
 */
public class InNullException extends RuntimeException {
    private final int code=102;
    public InNullException(String s) {
        super(s);
    }

    public int getCode() {
        return code;
    }
}