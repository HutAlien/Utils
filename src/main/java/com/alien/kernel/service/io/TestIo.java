package com.alien.kernel.service.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/6 09:23
 * @Description:
 */
public class TestIo {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        OutputStream outputStream = new FileOutputStream("D:/hello");
        for (Integer o : list) {
            try {
                outputStream.write(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取properties文件
     *
     * @param
     * @return
     */
    @Test
    public void PropertiesTest() {
        Properties properties = new Properties();
        //1 使用class变量的getResourceAsStream()方法
        InputStream is = getClass().getClassLoader().getResourceAsStream("location.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
