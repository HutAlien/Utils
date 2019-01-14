package com.example.alien.utils.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.json.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/14 15:11
 * @Description:
 */
@Slf4j
public class ListTest {

    //list排序
    @Test
    public void TestList() {
        List<Date> list = new ArrayList<>();
        Date date = new Date();
        log.info("date={}", Json.toJson(date));
        Date date1 = new Date(date.getTime() + 10000);
        log.info("date1={}", date1);
        Date date2 = new Date(date1.getTime() + 10000);
        log.info("date2={}", date2);
        list.add(date);
        list.add(date1);
        list.add(date2);
        list.sort((o1, o2) -> o2.compareTo(o1));        //从大到小
        log.info("sort={}", Arrays.toString(list.toArray()));
        //
        AtomicReference<Date> atomicReference = new AtomicReference<>();
        list.forEach((date3) -> {
            if (date3.getTime() == date.getTime()) {
                atomicReference.set(date3);
            }
        });
        log.info("atomicReference={}",atomicReference);
    }


}
