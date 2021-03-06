package com.alien.kernel.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 15:29
 * @Description: 字符串工具类
 */
@Slf4j
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
    public static <T> boolean isTranslate(T... param) {
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

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("a");
        set.add("a");
        for (Object s : set) {
            System.out.println(s);
        }
        //System.out.println(getUUID());
    }

}
