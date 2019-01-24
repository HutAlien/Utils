package com.example.alien.utils.utils.java8;

import com.example.alien.utils.entity.Employee;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


/**
 * @Author QWE
 * @Date 2018/11/21 20:55
 * @Description
 **/
@Slf4j
public class Test {

    public static void TestCollection() {
        Map<String, Object> map = new HashMap<>();
        map.put("A", 100);
        map.put("B", 200);
        map.put("C", 500);
        for (Map.Entry<String, Object> entry : map.entrySet()) {

        }
        map.forEach((k, v) -> {
            System.out.println(k + "  " + v);
        });
        map.forEach((k, v) -> {
            System.out.println(k + "" + v);
            if (k.equals("B")) {
                System.out.println("Hello B");
            }
        });

    }

    public static void TestThread() {
        Runnable runnable = () -> {
            System.out.println("用拉姆达表达式创建线程");
        };
        new Thread(runnable).start();
        new Thread(() -> {
            System.out.println("这是用拉姆达表达式创建的线程1");
        }).start();
    }

    public static void main(String[] args) {
        // Test.TestCollection();
        //Test.TestThread();
        List<Employee> list = Lists.newArrayList();
        Employee employee = new Employee(1, "Tom", 12, 111.0);
        Employee employee1 = new Employee(1, "Jerry", 12, 111.0);
        Employee employee2 = new Employee(1, "Tom", 12, 111.0);
        list.add(employee);
        list.add(employee1);
        list.add(employee2);
        Map<String, Integer> map = Maps.newHashMap();
        for (Employee e : list) {
            map.merge(e.getName(), e.getAge(), (a, b) -> a + b);
        }
    }

    @org.junit.jupiter.api.Test
    public void TestFunction() {
        //引用无参构造器
        Supplier<Employee> supplier = Employee::new;
        Employee employee = supplier.get();
        //引用全参构造器(自定义函数式接口)   不将构造函数实例化却能引用
        EmpFunction<Integer, String, Integer, Double, Employee> empFunction = Employee::new;
        Employee employee1 = empFunction.get(1, "alien", 22, 4000.0);
        log.info("employee1={}", employee1);
        //反射获取构造器
        Class c = employee.getClass();
        try {
            //获取(带参数)构造器
            Constructor constructor = c.getConstructor(Integer.class, String.class, Integer.class, Double.class);
            log.info("constructor={}", constructor.getName());
            Employee employee2 = (Employee) constructor.newInstance(2, "alien1", 22, 111.1);  //创建对象
            log.info("employee2={}", employee2);
            //获取全部的构造器
            Constructor[] constructors = c.getConstructors();
            Arrays.stream(constructors).forEach((o) -> System.out.println(o.getName()));

            //访问方法
            Method[] method = c.getMethods();                   //获得类的public类型的方法。这里包括 Object 类的一些方法
            Method[] methods = c.getDeclaredMethods();          //获取类的所有方法
            Arrays.stream(method).forEach((o) -> System.out.println(o.getName()));
            //获取方法名为setAge 参数类型为Integer的方法
            Method method1 = c.getMethod("setAge", Integer.class);
            log.info("setAge={}", method1);
            //调用方法
            method1.invoke(employee2, 33);

            //访问属性
            Field[] fields = c.getDeclaredFields();     //获取类所有属性
            Arrays.stream(fields).forEach((o) -> System.out.println(o.getName()));          //getField 只能获取public的，包括从父类继承来的字段。
            //获取指定属性
            Field field = c.getDeclaredField("name");
            //log.info("name={}", field);
            field.setAccessible(true);//设置允许访问私有属性值
            //
            Field[] fields1 = c.getFields();          //getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。 (注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
           // Field field1 = c.getField("name");
            //将employee2对象的name属性值设置为Bob
            field.set(employee2,"Bob");
            log.info("employee2={}",employee2);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
