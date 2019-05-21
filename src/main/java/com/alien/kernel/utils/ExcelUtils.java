package com.alien.kernel.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alien.kernel.dto.AjaxResult;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel 工具类
 *
 * @author Somer
 * @date 2018-08-27 16:41
 **/
public class ExcelUtils {

    /**
     * Excel 导入解析为 List
     *
     * @param file   导入的文件
     * @param params 导入参数配置
     * @param clazz  解析后的实体类
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, ImportParams params, Class<T> clazz) {
        InputStream inputStream = null;
        List<T> list = null;
        try {
            inputStream = file.getInputStream();
            list = ExcelImportUtil.importExcel(inputStream, clazz, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * Excel 导出(下载)
     *
     * @param response
     * @param list      要导出的数据集合
     * @param clazz     对应的实体类
     * @param fileName  导出文件名
     * @param title     Excel 标题
     * @param sheetName sheet名称
     */
    public static void exportExcel(HttpServletResponse response, List<?> list, Class<?> clazz, String fileName,
                                   String title, String sheetName) {
        OutputStream outputStream = null;
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), clazz, list);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导入解析工具
     *
     * @param
     * @return
     */
    public static <T> AjaxResult importResolver(MultipartFile file, Class<T> clazz) {
        if (file == null) {
            return new AjaxResult(201, "请选择文件");
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        List<T> list = importExcel(file, params, clazz);
        if (list == null || list.size() <= 0) {
            return new AjaxResult(201, "暂无数据");
        }
        return new AjaxResult(200, "解析成功", list, list.size());
    }
}