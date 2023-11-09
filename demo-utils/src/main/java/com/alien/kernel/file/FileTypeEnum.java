package com.alien.kernel.file;

import cn.hutool.core.io.FileMagicNumber;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/10/9 14:44
 * @version:
 */
@Getter
public enum FileTypeEnum {

    /**
     *
     */
    JPEG("JPEG", "FFD8FF"),

    /**
     *
     */
    PNG("PNG", "89504E47");

    private String type;
    private String val;

    FileTypeEnum(String type, String val) {
        this.type = type;
        this.val = val;
    }


    /**
     * 获取文件头
     *
     * @param multipartFile
     * @return String
     */
    private static String getFileHeader(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        byte[] bytes = new byte[28];
        inputStream.read(bytes, 0, 28);
        inputStream.close();
        return bytes2hex(bytes);
    }

    /**
     * 获取文件头-文件路径
     *
     * @param filePath
     * @return String
     */
    private static String getFileHeader(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        byte[] bytes = new byte[28];
        inputStream.read(bytes, 0, 28);
        inputStream.close();
        return bytes2hex(bytes);
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param bytes
     * @return String
     */
    private static String bytes2hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (byte b : bytes) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 也可以使用FileMagicNumber中的match方法匹配，判断文件类型
     * FileMagicNumber.PNG.match(bytes)
     *
     * 文件头枚举：https://zhuanlan.zhihu.com/p/571208394?utm_id=0
     *
     */
    public static void main(String[] args) throws IOException {
        String path="C:\\Users\\DELL\\Desktop\\449BF99C-A38F-4c8e-8276-6DBB8244504E(1).png";
        System.out.println(getFileHeader(path));
        //使用FileMagicNumber
        InputStream inputStream=new FileInputStream(path);
        byte[] bytes=new byte[4096];
        inputStream.read(bytes,0,bytes.length);
        System.out.println(FileMagicNumber.PNG.match(bytes));
    }
}
