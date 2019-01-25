package com.alien.kernel.collection;

import com.alien.kernel.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/25 09:23
 * @Description:
 */
@Slf4j
public class DateTest {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();      //获取当前时间
        log.info("now={}", now);
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        log.info("localDate={}", localDate);
        int year = localDate.getYear();              //获取年
        Month mouth = localDate.getMonth();          //获取月份
        DayOfWeek day = localDate.getDayOfWeek();    //获取天(星期几)
        int len = localDate.lengthOfMonth();           //当前月有多少天
        boolean leap = localDate.isLeapYear();         //
        log.info("year={},mouth={},day={},len={}", year, mouth, day, len);
        //
        int year1 = now.get(ChronoField.YEAR);
        int mouth1 = now.get(ChronoField.MONTH_OF_YEAR);
        int day1 = now.get(ChronoField.DAY_OF_MONTH);
        log.info("year1={},mouth1={},day1={}", year1, mouth1, day1);
        //
        LocalTime time = LocalTime.now();
        LocalTime time1 = LocalTime.parse("09:41:29");
        log.info("time={},time1={}", time, time1);
        //
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime.getNano());
        System.out.println(DateUtils.getCurrentTime().getTime());
        LocalDateTime localDateTime1 = LocalDateTime.of(now, time);
        String s=localDateTime1.format( DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
        System.out.println(s);
        //
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        //
        Instant instant=Instant.now();
        System.out.println(instant);
        System.out.println(Date.from(instant));
        //时钟
        Clock clock=Clock.systemUTC();
        System.out.println(clock);
        Clock clock1=Clock.tickMinutes(ZoneId.of("Asia/Shanghai"));
        System.out.println(clock1);

    }
}
