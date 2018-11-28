package com.example.alien.utils.service.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/27 12:41
 * @Description:
 */
@Component
public class ScheduleTask {
    private int count = 0;

    @Scheduled(cron = "*/6 * * * * ?") //每隔6秒执行一次
    private void process() {
        System.out.println(count++);
    }

    @Scheduled(fixedRate = 6000)
    private void process1() {
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
        System.out.println("现在的时间:"+ sdf.format(new Date()));
    }

}
