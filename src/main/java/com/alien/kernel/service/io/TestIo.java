package com.alien.kernel.service.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/6 09:23
 * @Description:
 */
public class TestIo {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        OutputStream outputStream = new FileOutputStream("D:/hello");
        for (Integer o:list){
            try {
                outputStream.write(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
