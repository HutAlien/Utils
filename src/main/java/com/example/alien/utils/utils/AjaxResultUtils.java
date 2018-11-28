package com.example.alien.utils.utils;

import com.example.alien.utils.dto.AjaxCode;
import com.example.alien.utils.dto.AjaxResult;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/22 11:29
 * @Description:
 */
public class AjaxResultUtils {
    public static<T> AjaxResult get(T t){
        if (t!=null){
            return new AjaxResult(AjaxCode.SUCCESS,AjaxCode.SUCCESS_MESSAFE,t);
        }
        return new AjaxResult(AjaxCode.FAILURE,AjaxCode.NO_DATA);
    }
}
