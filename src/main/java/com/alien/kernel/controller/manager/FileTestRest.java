package com.alien.kernel.controller.manager;

import com.alien.kernel.dto.AjaxCode;
import com.alien.kernel.dto.AjaxResult;
import com.alien.kernel.exception.CustomException;
import com.google.common.collect.Lists;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Auther: FYJ
 * @Date: 2019/5/25 11:15
 * @Description:
 */
@RequestMapping("/file")
@RestController
public class FileTestRest {

    private static Logger logger = LoggerFactory.getLogger(FileTestRest.class);

    @PostMapping
    public AjaxResult fileUplod(MultipartFile zipFile) {
        String fileName = zipFile.getOriginalFilename();
        String filePath = "D:\\test";//zip文件暂存路径
        File file = new File(filePath + File.separator + fileName);
        //
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            IOUtils.copy(zipFile.getInputStream(), fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fos != null;
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new AjaxResult(AjaxCode.SUCCESS, "写入成功");
    }

    /**
     * zip压缩文件解压
     *
     * @param zipFilePath 解压文件路径
     * @throws FileNotFoundException 解压文件路径错误
     */
    public static void decompression(String zipFilePath) throws FileNotFoundException {
        File file = new File(zipFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("zipFilePath");
        }
        try (ZipInputStream zipInputStream = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(file)), Charset.forName("GBK"))) {
            File fout;
            String parent = file.getParent();
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {     //解压目录
                    String name = zipEntry.getName();
                    name = name.substring(0, name.length() - 1);
                    File f = new File(parent + File.separator + name);
                    f.mkdir();
                    System.out.println("mkdir：" + parent + File.separator + name);
                    //扫描目录中的文件,拿到文件流
                    //List<String> fileNames = scanDirAllFile(f);
                    File[] files=f.listFiles();
                    for (File o : files) {
                        int temp = 0;
                        File saveFile = new File(parent + File.separator + o.getName());
                        byte[] bytes = new byte[1024];
                        try (FileInputStream fis = new FileInputStream(o);
                             FileOutputStream fos = new FileOutputStream(saveFile)) {
                            while ((temp = fis.read(bytes)) != -1) {
                                fos.write(bytes);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    fout = new File(parent, zipEntry.getName());
                    if (!fout.exists()) {
                        (new File(fout.getParent())).mkdirs();
                    }
                    try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                            new FileOutputStream(fout))) {
                        int read;
                        byte[] buffer = new byte[1024];
                        while ((read = zipInputStream.read(buffer)) != -1) {
                            bufferedOutputStream.write(buffer, 0, read);
                            bufferedOutputStream.flush();
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 遍历获取目录下所有文件名称
     *
     * @param dir 待扫描目录
     */
    public static List<String> scanDirAllFile(File dir) {
        List<String> fileNames = Lists.newArrayList();
        if (dir == null || !dir.exists()) { // 目录为空
            throw new CustomException("目录为空");
        }
        if (dir.isDirectory()) {
            File[] files = dir.listFiles(); // 获得当前目录下所有文件以及文件夹
            if (files != null) {
                for (File file : files) {   // 递归
                    scanDirAllFile(file);
                }
            }
        } else {                            // 文件
            logger.debug("fileName = {}", dir.getName());
            fileNames.add(dir.getName());
        }
        return fileNames;
    }

    /**
     * 遍历获取目录下所有文件，写到当前文件目录下
     *
     * @param dir 待扫描目录
     */
    public static void scanDirAllFileWrite(File dir) {
        byte[] bytes = new byte[1024];
        int temp = 0;
        if (dir == null || !dir.exists()) { // 目录为空
            throw new CustomException("目录为空");
        }
        if (dir.isDirectory()) {
            File[] files = dir.listFiles(); // 获得当前目录下所有文件以及文件夹
            if (files != null) {
                for (File file : files) {   // 递归
                    scanDirAllFileWrite(file);
                }
            }
        } else { // 文件
            logger.debug("fileName = {}", dir.getName());
            try {
                String path = dir.getPath();
                FileInputStream fis = new FileInputStream(path);//文件输入流
                while ((temp = fis.read(bytes)) != -1) {


                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把文件从一个地方写到另一个地方
     *
     * @param
     * @return
     */
    public static void writeFile(File file, File targetFile) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        byte[] bytes = new byte[1024];
        int temp = 0;
        while ((temp = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes);
            outputStream.flush();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }

    /**
     * 字节流读取文件 bufferedInputStream
     * BufferedInputStream是套在某个其他的InputStream外，起着缓存的功能
     *
     * @param
     * @return
     */
    public static void readFile(File file) {
        byte[] bytes = new byte[(int) file.length()];
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(bytes);
            String contex = new String(bytes);
            System.out.println(contex);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bufferedInputStream != null;
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 字符流读取文件  BufferedReader
     * <p>
     * 用字节流读取中文的时候，可能会出现乱码，而用字符流则不会出现乱码，而且用字符流读取的速度比字节流要快；
     *
     * @param
     * @return
     */
    public static void readFileByReader(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();
        String s;
        while ((s = bufferedReader.readLine()) != null) {  //一次读一行
            sb.append(s);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        decompression("E:\\test\\学生照片.zip");
    }
}
