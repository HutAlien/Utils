package com.alien.kernel.dto;

import lombok.Data;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 13:14
 * @Description:
 */
@Data
public class AjaxResult {
    private Integer code;//状态码
    private String message;
    private Object data;
    private Integer total;

    public AjaxResult() {
    }

    public AjaxResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public AjaxResult(Integer code, String message, Object data, Integer total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }
}
