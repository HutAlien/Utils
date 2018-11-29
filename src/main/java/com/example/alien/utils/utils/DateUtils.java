package com.example.alien.utils.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/28 16:15
 * @Description: 时间工具类
 */
public class DateUtils {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
    public static String[] week={""};

    /**
     * 获取当前系统时间(精确到毫秒)
     *
     * @param
     * @return
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getCurrentTime1() {
        return sdf.format(new java.util.Date());
    }

    /**
     * 获取当前日期
     *
     * @param
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentTime().toString().substring(0, 10);
    }

    /**
     * 获取当前系统时间(去掉毫秒)
     *
     * @param
     * @return
     */
    public static String getCurrentTimes() {
        String time = getCurrentTime().toString();
        return time.substring(0, time.lastIndexOf("."));
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.getCurrentTime());

    }
}
