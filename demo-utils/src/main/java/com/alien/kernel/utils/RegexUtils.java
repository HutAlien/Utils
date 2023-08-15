package com.alien.kernel.utils;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/4/17 10:20
 * @version:
 */
public class RegexUtils {

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^(1[3-9])\\d{9}$";

    /**
     * 正则表达式：验证座机号
     */
    public static final String REGEX_TEL = "^(((\\d{3,4}-)?[0-9]{7,8})|(1(3|4|5|6|7|8|9)\\d{9}))$";
    /**
     * 匹配正常11位手机|11+4的隐私号
     */
    public static final String REGEX_CUSTOMER_PHONE_IMPORT="^1[3456789]\\d{9}-\\d{4}$|^1[3456789]\\d{9}$";
    /**
     * 汉字，字母数字下划线以及指定符号：-!@#¥%*&^$()
     */
    public static final String REGEX_CUSTOMER_NAME_IMPORT="^[\\u4e00-\\u9fa5_\\-!@#¥%*&^$()\\[\\]?:a-zA-Z0-9]+$";


    public static void main(String[] args) {
        System.out.println(Pattern.matches(REGEX_TEL,"(0571)28059570"));
    }
}
