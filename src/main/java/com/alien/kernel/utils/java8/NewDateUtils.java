package com.alien.kernel.utils.java8;

import com.google.common.collect.Maps;

import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/25 11:21
 * @Description: 时间工具类
 */
public class NewDateUtils {

    private static Map<String, String> map;

    static {
        map = Maps.newHashMap();
        map.put("MONDAY", "星期一");
        map.put("TUESDAY", "星期二");
        map.put("WEDNESDAY", "星期三");
        map.put("THURSDAY", "星期四");
        map.put("FRIDAY", "星期五");
        map.put("SATURDAY", "星期六");
        map.put("SUNDAY", "星期日");
    }

    /**
     * 获取当前时间
     *
     * @param
     * @return
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前日期
     *
     * @param
     * @return YYYY-MM-dd
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间
     *
     * @param
     * @return hh:mm:ss:nnn
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前年
     *
     * @param
     * @return
     */
    public static int getCurrentYear() {
        return getCurrentDateTime().getYear();
    }

    /**
     * 获取当前月份
     *
     * @param
     * @return
     */
    public static int getCurrentMouth() {
        return getCurrentDateTime().getMonthValue();
    }

    /**
     * 获取当前天
     *
     * @param
     * @return
     */
    public static int getCurrentDay() {
        return getCurrentDateTime().getDayOfMonth();
    }

    /**
     * 获取当前是星期几
     *
     * @param
     * @return
     */
    public static DayOfWeek getCurrentDayOfweek() {
        return getCurrentDateTime().getDayOfWeek();
    }


    /**
     * 根据日期字符串转换为日期
     *
     * @param time 日期格式 YYYY-MM-dd
     * @return
     */
    public static LocalDate getDateByTime(String time) {
        return LocalDate.parse(time);
    }

    /**
     * 根据日期获取日期是星期几
     *
     * @param time 日期格式 YYYY-MM-dd
     * @return
     */
    public static DayOfWeek getDayOfWeekByTime(String time) {
        return getDateByTime(time).getDayOfWeek();
    }

    /**
     * 根据日期获取日期是星期几
     *
     * @param time 日期格式 YYYY-MM-dd
     * @return String
     */
    public static String getDayOfWeekByTimes(String time) {
        return NewDateUtils.map.get(getDateByTime(time).getDayOfWeek().toString());
    }


    /**
     * 根据日期获取日期是一年中的第几天
     *
     * @param time 日期格式 YYYY-MM-dd
     * @return
     */
    public static int getDaysByDate(String time) {
        return getDateByTime(time).getDayOfYear();
    }

    /**
     * 根据时间字符串转换为时间类型
     *
     * @param time 时间格式 HH:mm:ss.nnn
     * @return
     */
    public static LocalTime getTimeByStrTime(String time) {
        return LocalTime.parse(time);
    }

    /**
     * 根据日期获取星期数（第几个星期）
     *
     * @param time 日期格式 YYYY-MM-dd
     * @return
     */
    public static int getWeeksByDate(String time) {
        return (getDateByTime(time).getDayOfYear() / 7) + 1;
    }

    /**
     * 将LocalDateTime转换为DATE
     *
     * @param
     * @return
     */
    public static Date getDateByLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param date 转 localdate
     * @return
     */
    public static LocalDate UDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * @param date 转 localTime
     * @return
     */
    public static LocalTime UDateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }

    /**
     * date 转TimeStamp
     *
     * @param
     * @return
     */
    public static Timestamp getTimeStampByDate(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * timestamp 转 LocalDateTime
     *
     * @param
     * @return
     */
    public static LocalDateTime getLocalDateTimeByTimestamp(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }


    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,3);
        calendar.add(Calendar.YEAR,1);
        System.out.println(new Timestamp(calendar.getTime().getTime()));
    }

}
