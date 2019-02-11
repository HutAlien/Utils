package com.alien.kernel.exception;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/21 15:12
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class RExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public AjaxResult CustomExceptionHandle(CustomException e) {
        return new AjaxResult(AjaxCode.FAILURE, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public AjaxResult BingingResultHandle(BindException e) {
        log.info("error", e.getBindingResult());
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuffer sb = new StringBuffer();
        for (FieldError field : errors) {
            sb.append(field.getField() + ":" + field.getDefaultMessage() + ";");
        }
        String message = sb.substring(0, sb.lastIndexOf(";"));
        return new AjaxResult(AjaxCode.FAILURE, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult ConstraintVilationExceptionHandle(ConstraintViolationException e) {
        return new AjaxResult(AjaxCode.FAILURE, e.getMessage());
    }

}
