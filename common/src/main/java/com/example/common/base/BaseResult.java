package com.example.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/11 16:50
 * @version:
 */
@Data
public class BaseResult<T> implements Serializable {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回码描述
     */
    private String msg;
    /**
     * 详细返回码
     */
    private String subCode;
    /**
     * 详细返回码描述
     */
    private String subMsg;
    /**
     * 返回数据对象
     */
    private T data;


    public BaseResult() {
    }

    public BaseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(Integer code, String msg, String subCode, String subMsg, T data) {
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.data = data;
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(BaseResultCodeEnum.SUCCESS.code, BaseResultCodeEnum.SUCCESS.getMessage(),data);
    }

    public static <T> BaseResult<T> status(boolean flag) {
        return flag ? success(null) : fail(null);
    }

    public static <T> BaseResult<T> fail(T data) {
        return new BaseResult<>(BaseResultCodeEnum.FAILURE.code, BaseResultCodeEnum.FAILURE.getMessage(),data);
    }

    public static <T> BaseResult<T> fail(String msg) {
        return new BaseResult<>(BaseResultCodeEnum.FAILURE.code,msg,null);
    }
}
