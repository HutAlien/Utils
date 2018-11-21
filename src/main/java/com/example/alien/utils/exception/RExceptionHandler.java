package com.example.alien.utils.exception;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;
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
