package com.alien.kernel.utils;

import com.alien.kernel.dto.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/24 16:41
 * @Description:
 */
public class fileUtils {


    private static Logger logger = LoggerFactory.getLogger(fileUtils.class);

    /**
     * 解压zip
     *
     * @param
     * @return
     */
    public static List<FileModel> unzip(MultipartFile file) {
        // 判断文件是否为zip文件
        String filename = file.getOriginalFilename();
        if (!filename.endsWith("zip")) {
            logger.info("传入文件格式不是zip文件" + filename);
        }
        List<FileModel> fileModelList = new ArrayList<FileModel>();
        String zipFileName = null;
        // 对文件进行解析
        try {
            ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream(), Charset.forName("GBK"));
            BufferedInputStream bs = new BufferedInputStream(zipInputStream);
            ZipEntry zipEntry;
            byte[] bytes = null;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) { // 获取zip包中的每一个zip file entry
                zipFileName = zipEntry.getName();
                Assert.notNull(zipFileName, "压缩文件中子文件的名字格式不正确");
                FileModel fileModel = new FileModel();
                fileModel.setFileName(zipFileName);
                bytes = new byte[(int) zipEntry.getSize()];
                bs.read(bytes, 0, (int) zipEntry.getSize());
                InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                fileModel.setFileInputstream(byteArrayInputStream);
                fileModelList.add(fileModel);
            }
        } catch (Exception e) {
            logger.error("读取部署包文件内容失败,请确认部署包格式正确:" + zipFileName, e);
        }
        return fileModelList;
    }

    /**
     * 具体实现文件压缩
     *
     * @param zipOutput
     * @param file      待压缩文件或文件夹
     * @param base
     */
    private static void zip(ZipOutputStream zipOutput, File file, String base) {
        ZipEntry zipEntry = new ZipEntry(base + File.separator + file.getName());  //ZipEntry为即将要压缩文件的文件信息
        BufferedInputStream bufferedInputStream = null;
        try {
            zipOutput.putNextEntry(zipEntry);  //将其添加到压缩包中，准备进行压缩
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = bufferedInputStream.read(buffer)) != -1) {
                zipOutput.write(buffer, 0, read);
            }
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }

    }

}
