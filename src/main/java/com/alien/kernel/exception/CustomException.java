package com.alien.kernel.exception;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 15:09
 * @Description:
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
