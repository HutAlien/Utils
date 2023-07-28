package com.example.nacos.restcontroller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/27 16:31
 * @version:
 */
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {


    /**
     * @param id
     * @return List<String>
     */
    @GetMapping
    public List<String> getItemList(@RequestParam String id) {
        return Lists.newArrayList("1", "2", "3");
    }
}
