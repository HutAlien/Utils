package com.alien.kernel.utils.java8;

import com.alien.kernel.entity.Employee;
import com.alien.kernel.utils.StringUtils;

import java.util.Optional;

/**
 * @Auther: FYJ
 * @Date: 2019/9/16 14:07
 * @Description:
 */
public class NewFeatures {

    private String name;

    private static String getInfo(Employee employee) {
        //Optional<Employee> optional=Optional.ofNullable(employee); 创建一个optional对象，不允许为空,为空则返回空
        //Optional.of() 创建一个optional对象，为空则抛出异常
        //return Optional.ofNullable(employee).map((e)->e.getName()).orElse("name is null");
        return Optional.ofNullable(employee).map((e) -> e.getName()).orElseGet(() -> "jack");//orElseGet()给对象赋值
    }

    private void set(String name) {
        this.name = Optional.ofNullable(name).filter(StringUtils::isTranslate).orElseThrow(() -> new IllegalArgumentException());
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("Tom");
        System.out.println(getInfo(employee));
        //
        NewFeatures newFeatures = new NewFeatures();
    }
}
