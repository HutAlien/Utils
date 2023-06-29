package com.alien.kernel.service.io;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: FengYunJun
 * @Date: 2019/6/13 10:34
 * @Description:
 */
public class Nio {

    public static void main(String[] args) {
        //read
        try (FileInputStream fileInputStream = new FileInputStream("E://test.txt")) {
            FileChannel channel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wirte() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("E://hello.txt")) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            FileChannel channel = fileOutputStream.getChannel();
            String[] messages = {"Hello", "NIO", "1"};
            for (String s : messages) {
                buffer.put(s.getBytes());
            }
            buffer.flip();
            channel.write(buffer);
            System.out.println("写入完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
