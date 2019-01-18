package com.example.alien.utils.collection;

import com.example.alien.utils.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nutz.json.Json;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(1, "ton", 110, 10.1),
                new Employee(1, "ton", 12, 113.1), new Employee(1, "ton", 1, 1.1)));
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
        //
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
    }

    @Test
    public void computeListTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 1, 6, 7, 8, 33, 12);
        IntSummaryStatistics stati = list.stream().mapToInt((x) -> x).summaryStatistics();
        log.info("list Max={}", stati.getMax());
        log.info("list min={}", stati.getMin());
        log.info("list avg={}", stati.getAverage());
        log.info("list sum={}", stati.getSum());
        log.info("list count={}", stati.getCount());
        log.info("list stati={}", stati);

    }

    @Test
    public void ListDel() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 2, 3));
        //filter()
        List<Integer> tempList = list.stream().filter(o -> o > 3).limit(3).collect(Collectors.toList());
        log.info("tempList={}", tempList);
        List<Integer> tempList1 = list.stream().filter(a -> a > 1).distinct().skip(2).collect(Collectors.toList());//skin(n) 跳过前N个数
        log.info("tempList1={}",tempList1);

        //流支持map()方法，他会接收一个函数作为参数    //flatmap()
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee(1, "ton", 110, 10.1),
                new Employee(2, "ton1", 12, 113.1), new Employee(3, "ton2", 1, 1.1)));
        List<Integer> names=employees.stream().map(Employee::getName).map(String::length).collect(Collectors.toList());
        log.info("names={}",names);



    }

    @Test
    public void TestAdd() {

    }
}
