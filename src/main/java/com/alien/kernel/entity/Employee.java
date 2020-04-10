package com.alien.kernel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/14 13:05
 * @Description:
 */
@Data
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    public Integer age;
    private Double salary;

    public Employee() {
    }
}
