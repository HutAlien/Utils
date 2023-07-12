package com.example.mysql;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mysql.base.BaseTestConfig;
import com.example.mysql.controller.ItemController;
import com.example.mysql.domain.entity.Item;
import com.example.mysql.service.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MysqlApplicationTests {

    @Autowired
    ItemController itemController;

    private MockMvc mockMvc;

    @Autowired
    private IItemService itemService;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void addTest() {
        Item item = new Item();
        item.setItemName("mysql-test");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/item")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(item));
        BaseTestConfig.getResult(mockMvc, builder);
    }

    @Test
    public void queryById(){
        Page<Item> page = new Page<>(2, 1);
        IPage<Item> pageList = itemService.page(page);
        log.info("pageList={}",pageList);
    }


}
