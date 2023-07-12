package com.example.mysql.code;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/11 10:58
 * @version:
 */
public class CodeGenerator {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/utils?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    /**
     * 3.5.2 交互式生成
     *
     * @param args args
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        String moduleName="\\datasource\\mysql\\";
        // 输出基础路径
        String outputBasePath = System.getProperty("user.dir") + moduleName + "/src/main/java";
        // xml文件输出路径[重置xml输出路径，否则将基于基础路径输出]
        String xmlPath = System.getProperty("user.dir") + moduleName + "/src/main/resources/mapper";
        FastAutoGenerator.create(DATABASE_URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称[回车确认]："))
                        .enableSwagger()
                        .fileOverride()
                        .outputDir(outputBasePath)
                        .disableOpenDir())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.example.mysql")
                        .entity("domain.entity")
                        // 重置xml输出路径
                        .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath)))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请键入表名，多个英文逗号分隔，所有输入all[回车确认]：")))
                        .addTablePrefix(scanner.apply("请键入数据库表名前缀，多个英文逗号分隔，若无则随意键入并回车[无前缀时请注意，随意键入的字符不能与数据库表前缀冲突！]："))
                        .controllerBuilder()
                        //文件覆盖
                        .enableFileOverride()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .versionColumnName("version")
                        //创建时间和更新时间不由应用程序维护，交由数据库,设置为CURRENT_TIMESTAMP和on update CURRENT_TIMESTAMP
                        //.addTableFills(new Column("creater", FieldFill.INSERT), new Column("updater", FieldFill.UPDATE),
                        //      new Column("create_time", FieldFill.INSERT), new Column("update_time", FieldFill.UPDATE))
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .build())
                // 模板配置
                .templateConfig((scanner, builder) -> builder.controller("template/mybatis/controller.java.vm")
                        .entity("template/mybatis/entity.java.vm")
                        .service("template/mybatis/service.java.vm")
                        .serviceImpl("template/mybatis/serviceImpl.java.vm")
                        .mapper("template/mybatis/mapper.java.vm")
                        .build())
                // 执行
                .execute();
    }

    /**
     * 处理表为 all 情况
     *
     * @param tables 数据库表
     * @return List<String>
     */
    protected static List<String> getTables(String tables) {
        return "all".equalsIgnoreCase(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
