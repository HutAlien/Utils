package com.example.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/8/4 17:11
 * @version:
 */
@Data
@Document(indexName = "item", createIndex = false)
public class Item {

    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(analyzer = "ik_max_word")
    private String title;

    @Field(analyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date createTime;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date updateTime;
}
