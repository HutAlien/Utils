package com.alien.kernel.service.pattern.prototype;

import com.alien.kernel.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/15 14:43
 * @Description:
 */
@Data
@Builder
@Slf4j
@AllArgsConstructor
public class Prototype implements Cloneable, Serializable {

    public Prototype() {
    }

    private static final long serialVersion = 1L;

    private String name;

    private Employee employee;

    /**
     * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
     * <p>
     * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
     *
     * @param
     * @return
     */
    public Object clone() throws CloneNotSupportedException {//对象浅复制
        Prototype prototype = (Prototype) super.clone();
        return prototype;
    }

    /**
     * 对象深复制
     *
     * @param
     * @return
     */
    public Object deepClone() throws IOException, ClassNotFoundException {
        //写入当前对象的二进制流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        //读出二进制流产生的对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();

    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Prototype prototype = new Prototype();
        prototype.setName("Hello");
        prototype.setEmployee(new Employee());
        System.out.println(prototype);
        Prototype p = (Prototype) prototype.clone();
        Object object = p.deepClone();
        System.out.println(object);

    }

}
