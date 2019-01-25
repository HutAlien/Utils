package com.alien.kernel.exception;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 15:12
 * @Description:
 */
@RestControllerAdvice
public class RExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public AjaxResult CustomExceptionHandle(CustomException e){
        return new AjaxResult(AjaxCode.FAILURE,e.getMessage());
    }
}
