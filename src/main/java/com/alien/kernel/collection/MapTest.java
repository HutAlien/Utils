package com.alien.kernel.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
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
}
