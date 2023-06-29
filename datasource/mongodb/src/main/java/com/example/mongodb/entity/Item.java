package com.example.mongodb.entity;

import com.example.mongodb.enums.ItemPlatformEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/6/26 11:29
 * @version:
 */
@Data
@Document(collection = "item")
public class Item implements Serializable {

    /**
     *
     */
    @Id
    private String id;
    /**
     *
     */
    private String itemName;
    /**
     *
     */
    private BigDecimal price;
    /**
     *
     */
    @Field("platform")
    private ItemPlatformEnum platform;
    /**
     *
     */
    private LocalDateTime created;
    /**
     *
     */
    private LocalDateTime modified;
}
