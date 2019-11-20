package com.alien.kernel.service.schedule;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/27 12:41
 * @Description: 定时任务相关
 */
@Component
public class ScheduleTask implements SchedulingConfigurer {
    private int count = 0;

    // @Scheduled(cron = "*/6 * * * * ?") //每隔6秒执行一次
    private void process() {
        System.out.println(count++);
    }

    // @Scheduled(fixedRate = 6000)
    private void process1() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
        System.out.println("现在的时间:" + sdf.format(new Date()));
    }

    /**
     * SpringBoot实现定时任务的两种方式：
     * 一种是基于注解@Schedule的简单定时器,另一种是基于接口实现SchedulingConfigurer的动态定时任务
     *
     * @Param:
     * @return:
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {

        //添加基于cron触发的指定任务
        registrar.addCronTask(()->{
            System.out.println(LocalDateTime.now());
        },"*/6 * * * * ?");

        //添加固定速率任务
        registrar.addFixedRateTask(()->{
            System.out.println(LocalDateTime.now());
        },6000);

    }
}