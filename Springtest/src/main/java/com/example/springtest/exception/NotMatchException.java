package com.example.springtest.exception;

/**
 * 数据不匹配异常101
 */

public class NotMatchException extends RuntimeException {
    private final int code=101;
    public NotMatchException(String s) {
        super(s);
    }

    public int getCode() {
        return code;
    }
}
