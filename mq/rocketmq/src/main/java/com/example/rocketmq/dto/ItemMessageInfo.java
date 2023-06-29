package com.example.rocketmq.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/6/28 15:29
 * @version:
 */
@Data
public class ItemMessageInfo {
    /**
     *
     */
    private String id;
    /**
     *
     */
    private String itemName;
    /**
     *
     */
    private LocalDateTime created;
    /**
     *
     */
    private LocalDateTime modified;
}
