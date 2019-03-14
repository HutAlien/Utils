package com.alien.kernel.dto;

/**
 * @Auther: FengYunJun
 * @Date: 2019/2/19 11:10
 * @Description:
 */
public enum AjaxCodeEnum {
    SUCCESS(200, "获取成功"),
    FAILURE(201, "暂无数据");

    private Integer code;
    private String message;

    AjaxCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
