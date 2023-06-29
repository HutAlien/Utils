package com.alien.kernel.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * @Auther: FengYunJun
 * @Date: 2019/5/24 12:19
 * @Description:
 */
@Data
public class FileModel {
    String fileName;
    String fileType;
    InputStream fileInputstream;

}
