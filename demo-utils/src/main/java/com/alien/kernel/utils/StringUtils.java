package com.alien.kernel.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
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

    /**
     *
     * 获取真实的ip地址
     * @param request
     * @return String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void main(String[] args) {
        int i = 100_00;//语法糖
        System.out.println(i);
        StringBuffer sb = new StringBuffer("dsdsds");

        System.out.println(sb.toString());
        //Guava的布隆过滤器
        BloomFilter<Integer> filter=BloomFilter.create(Funnels.integerFunnel(),100,0.01);
        System.out.println(filter.mightContain(1));
        filter.put(1);
        System.out.println(filter.mightContain(1));
    }

}
