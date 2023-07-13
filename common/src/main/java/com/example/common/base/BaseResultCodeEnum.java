package com.example.common.base;

import java.io.Serializable;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/11 17:22
 * @version:
 */
public enum BaseResultCodeEnum implements Serializable {
    SUCCESS(200, "操作成功"),
    FAILURE(201, "业务异常"),

    ;

    final int code;
    final String message;

    BaseResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
