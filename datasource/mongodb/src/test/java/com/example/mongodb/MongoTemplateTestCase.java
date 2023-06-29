package com.example.mongodb;

import com.example.mongodb.entity.Item;
import com.example.mongodb.enums.ItemPlatformEnum;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/6/26 14:49
 * @version:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MongoTemplateTestCase {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void insertOne() {
        Item item = new Item();
        item.setItemName("测试1");
        item.setPrice(new BigDecimal("1.22"));
        item.setPlatform(ItemPlatformEnum.TB);
        item.setCreated(LocalDateTime.now());
        item.setModified(LocalDateTime.now());
        mongoTemplate.insert(item);
        log.info("item={}",item);
    }

    @Test
    public void query() {
        List<Item> list = mongoTemplate.findAll(Item.class);
        log.info("list={}", list);
        Item item = mongoTemplate.findById("64994408566d302c24d5c555", Item.class);
        log.info("item={}", item);
        //条件查询
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("itemName").regex("j"));
        Query query = new Query(criteria);
        list = mongoTemplate.find(query, Item.class);
        log.info("list={}", list);

    }

    @Test
    public void del() {
        DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where("id").is("64993c17566d303b0cb565e2")), Item.class);
        log.info("deleteResult={}", deleteResult);
    }


    @Test
    public void update(){
        Update update=new Update();
        update.set("item_name","junit");
        UpdateResult updateResult=mongoTemplate.updateMulti(new Query(),update,Item.class);
        log.info("updateResult={}",updateResult);
    }

}
