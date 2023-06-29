package com.alien.kernel.utils;

import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: alien
 * @date: 2019/12/31 16:23
 * @description: 反射工具类
 */
public class ReflectionUtils {

    /**
     * @param object   反射对象
     * @param attrName 属性名
     * @return: 属性值
     */
    public static Object getAttributeValueByName(Object object, String attrName) {
        if (StringUtils.isTranslate(attrName)) {
            Field[] fields = object.getClass().getDeclaredFields();//获取本类所有的字段，包括private的，但是不能获取继承来的字段。
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(attrName)) {
                    try {
                        return field.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "";
        Assert.notNull(s, "Context must not be null");
        class TUser {
            public int count;
            public String name;
        }
        TUser user = new TUser();
        user.count = 10;
        AtomicInteger atomicInteger = new AtomicInteger(user.count);
        atomicInteger.getAndIncrement();
        System.out.println(user.count);
    }

}
