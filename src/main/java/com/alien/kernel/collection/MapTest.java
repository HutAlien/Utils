package com.alien.kernel.collection;

import com.alien.kernel.entity.Employee;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/16 09:48
 * @Description:
 */
@Slf4j
public class MapTest {
    /**
     * compute() test
     * 统计字符串中每一个的 单词出现的次数
     */
    @Test
    public void computeTest() {
        Map<String, Integer> wordCounts = new HashMap<>(10);
        String s = "a b c d a s a c";

        for (String t : s.split(" ")) {
            wordCounts.compute(t, (k, v) -> {
                if (null == v) {
                    v = 0;
                }
                return v + 1;
            });
        }
        log.info("wordCounts = {}", wordCounts);
    }


    /**
     * computeIfPresent() test
     * 统计字符串中已存在单词出现的次数 和computeIfAbsent()区别就在于key取出val为空值以后操作
     */
    @Test
    public void computeIfPresentTest() {
        Map<String, Integer> wordCounts = new ConcurrentHashMap<>(10);
        String s = "a b c d a s a c";

        wordCounts.put("a", 0);
        for (String t : s.split(" ")) {
            wordCounts.computeIfPresent(t, (k, v) -> v + 1);
        }
        log.info("wordCounts = {}", wordCounts);

    }

    @Test
    public void computeIfAbsent() {
        Map<String, StringBuffer> resultMap = Maps.newHashMap();
        resultMap.put("A", new StringBuffer("this is A"));
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        list.forEach(item -> {
            resultMap.computeIfAbsent(item, key -> new StringBuffer("This is " + item)).append(" Hi");//这里可对key对应的value进行加工
        });
        log.info("resultMap={}", resultMap);
    }

    @Test
    public void TestMerge() {
        List<Employee> list = Lists.newArrayList();
        Employee employee = new Employee(1, "Tom", 12, 111.0);
        Employee employee1 = new Employee(1, "Jerry", 12, 111.0);
        Employee employee2 = new Employee(1, "Tom", 12, 111.0);
        list.add(employee);
        list.add(employee1);
        list.add(employee2);
        Map<String, Integer> map = Maps.newHashMap();
        list.stream().forEach((e) -> {
            map.merge(e.getName(), e.getAge(), (a, b) -> (a + b));//合并
        });
        log.info("map={}", map);
    }

    public static void main(String[] args) {
        Map<Integer, Object> map = Maps.newHashMap();
        map.put(0, "this is 0");
        for (int i = 0; i < 5; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((k, v) -> System.out.println(v));
        System.out.println(map.getOrDefault(12, "not found"));
    }
}
