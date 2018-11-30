package com.example.alien.utils.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 15:29
 * @Description: 字符串工具类
 */
public class StringUtils {
    /**
     * 参数是否全为空
     *
     * @param param
     * @return
     */
    public static <T> boolean isAllNull(T... param) {
        for (T t : param) {
            if (t != null || !"".equals(t) || !"null".equals(t) || !"undefined".equals(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 参数是否传递
     *
     * @param param
     * @return
     */
    public static<T> boolean isTranslate(T... param) {
        if (param.length <= 0) {
            return false;
        }
        for (T temp : param) {
            if (!"".equals(temp) || !"null".equals(temp) || !"undefined".equals(temp) || temp != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 参数是否都传递
     *
     * @param param
     * @return
     */
    public static <T> boolean isAllTranslate(T... param) {
        if (param.length <= 0) {
            return false;
        }
        for (T t : param) {
            if (t == null || "".equals(t) || "undefined".equals(t) || "null".equals(t)) {
                return false;
            }
        }
        return true;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(getUUID());

    }

}
