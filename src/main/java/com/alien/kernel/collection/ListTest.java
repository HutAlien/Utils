package com.alien.kernel.collection;

import com.alien.kernel.entity.Employee;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.json.Json;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/14 15:11
 * @Description:
 */
@Slf4j
public class ListTest {

    //list排序
    @Test
    public void TestList() {
        List<Date> list = new ArrayList<>();
        Date date = new Date();
        log.info("date={}", Json.toJson(date));
        Date date1 = new Date(date.getTime() + 10000);
        log.info("date1={}", date1);
        Date date2 = new Date(date1.getTime() + 10000);
        log.info("date2={}", date2);
        list.add(date);
        list.add(date1);
        list.add(date2);
        //
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee(1, "12ton", 110, 10.1),
                new Employee(1, "2ton", 12, 113.1),
                new Employee(1, "7ton", 1, 1.1)));
        employees.sort((o1, o2) -> o2.getAge().compareTo(o1.getAge())); //按年龄从大到小
        log.info("employees={}", employees);

        //以前的写法  从小到大
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        });
        log.info("employee1={}", employees);

        //也可以这样写
        employees.sort(comparing((a) -> a.getSalary()));//comparing函数接收一个Function来提取comparable的键值，并生成comparator对象
        employees.sort(comparing(Employee::getSalary));//方法引用
        log.info("employee={}", employees);

        employees.sort(comparing(Employee::getAge).reversed().thenComparing(Employee::getSalary));//当年龄一样时就按薪资排序
        //
        list.sort((o1, o2) -> o2.compareTo(o1));        //从大到小
        log.info("sort={}", Arrays.toString(list.toArray()));
        //
        AtomicReference<Date> atomicReference = new AtomicReference<>();   //原子操作
        list.forEach((date3) -> {
            if (date3.getTime() == date.getTime()) {
                atomicReference.set(date3);
            }
        });
        log.info("atomicReference={}", atomicReference);


        employees.stream().sorted(comparing((o) -> {
            return Integer.valueOf(o.getName().substring(0, o.getName().indexOf("t")));
        }));
        log.info("nameSort={}", employees);

    }

    @Test
    public void computeListTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 1, 6, 7, 8, 33, 12);
        IntSummaryStatistics stati = list.stream().mapToInt((x) -> x).summaryStatistics();
        IntSummaryStatistics statistics = list.stream().collect(summarizingInt((x) -> x));//使用summarizingInt 工厂方法来返回收集器
        log.info("list Max={}", statistics.getMax());
        log.info("list min={}", statistics.getMin());
        log.info("list avg={}", statistics.getAverage());
        log.info("list sum={}", statistics.getSum());
        log.info("list count={}", statistics.getCount());
        log.info("list stati={}", stati);
    }

    @Test
    public void ListDel() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 2, 3));
        List<Integer> tempList = list.stream().filter(o -> o > 3).limit(3).collect(Collectors.toList());
        log.info("tempList={}", tempList);

        List<Integer> tempList1 = list.stream().filter(a -> a > 1).distinct().skip(2).collect(Collectors.toList());//skin(n) 跳过前N个数
        log.info("tempList1={}", tempList1);

        int s = list.stream().reduce(0, Integer::sum);  //求和(暗含装箱成本)
        log.info("sum={}", s);

        //流支持map()方法，他会接收一个函数作为参数
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(1, "ton", 110, 10.1),
                new Employee(2, "ton1", 12, 113.1), new Employee(3, "ton2", 1, 1.1)));
        List<Integer> names = employees.stream().map(Employee::getName).map(String::length).collect(Collectors.toList());
        log.info("names={}", names);

        //规约
        int sum = list.stream().reduce(0, (a, b) -> a + b);
        log.info("sum={}", sum);

        Optional<Integer> max = list.stream().reduce(Integer::max);
        int sum1 = employees.stream().collect(summingInt(Employee::getAge));

        // 连接名字 字符串
        String Names = employees.stream().map(Employee::getName).collect(joining(","));
        log.info("names={}", Names);

        employees.forEach(employee -> {

        });

        employees.forEach((o)->{

        });

    }

    @Test
    public void TestAdd() {
        /**
         * 在直接使用Arrays.asList()生成list的时候，不能使用list集合的操作方法，会抛出异常，
         * 可以在外层包一层真正的ArrayList
         *
         * Arrays.asList()底层其实还是数组
         */
        //List<String> testList=Arrays.asList("a","b","c");
        List<String> list=new ArrayList<>(Arrays.asList("a","b","c","d"));
        list.forEach(System.out::println);
        list.add("c");
       list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> list= Lists.newArrayList("1","2","3");
        String[] target=list.toArray(new String[list.size()]);  //list转数组
        log.info("target={}",Json.toJson(target));
    }
}
