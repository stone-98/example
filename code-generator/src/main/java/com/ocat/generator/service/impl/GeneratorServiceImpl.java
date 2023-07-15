package com.ocat.generator.service.impl;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.ocat.generator.config.GeneratorConfig;
import com.ocat.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GeneratorServiceImpl implements GeneratorService, ApplicationRunner {
    
    @Autowired
    private GeneratorConfig config;
    
    @Autowired
    private DataSourceProperties dataSourceConfig;
    
    @Override
    public void generate() {
        //创建一个代码生成器
        FastAutoGenerator fastAutoGenerator =
                FastAutoGenerator.create(dataSourceConfig.getUrl(),
                        dataSourceConfig.getUsername(),
                        dataSourceConfig.getPassword());
    
        // 全局配置(GlobalConfig)
        globalConfig(fastAutoGenerator);
        packageConfig(fastAutoGenerator);
        strategyConfig(fastAutoGenerator);
        templateConfig(fastAutoGenerator);
    
        fastAutoGenerator.execute(); //执行以上配置
    }
    
    /**
     * 全局配置(GlobalConfig)
     */
    public void globalConfig(FastAutoGenerator generator) {
        generator.globalConfig(builder -> {
            builder
                    // 设置作者，可以写自己名字
                    .author(config.getAuthor())
                    // 开启 swagger 模式，这个是接口文档生成器，如果开启的话，就还需要导入swagger依赖
                    .enableSwagger()
                    // 覆盖已生成文件
                    .fileOverride()
                    //时间策略
                    .dateType(DateType.TIME_PACK)
                    //注释日期
                    .commentDate("yyyy-MM-dd")
                    // 指定输出目录，一般指定到java目录
                    .outputDir(config.getProjectPath() + "/src/main/java");
        });
    }
    
    /**
     * 包配置(PackageConfig)
     */
    public void packageConfig(FastAutoGenerator generator) {
        generator.packageConfig(builder -> {
            // 设置父包名
            builder.parent(config.getParentPackage())
                    // 设置父包模块名，这里一般不设置
                    .moduleName("")
                    // 设置mapperXml生成路径，这里是Mapper配置文件的路径，建议使用绝对路径
                    .pathInfo(
                            Collections.singletonMap(
                                    OutputFile.xml,
                                    config.getProjectPath() + "/src/main/resources/mapper")
                    );
        });
    }
    
    /**
     * 策略配置(StrategyConfig)
     */
    public void strategyConfig(FastAutoGenerator generator) {
        generator.strategyConfig(builder -> {
            builder.addInclude(config.getIncludeTable()); // 设置需要生成的表名
            //                            .addInclude("tbl_found") // 设置需要生成的表名
            //                            .addInclude("tbl_identify") // 设置需要生成的表名
            //                            .addInclude("tbl_lost") // 设置需要生成的表名
            //                            .addInclude("tbl_school") // 设置需要生成的表名
            //                            .addInclude("tbl_status") // 设置需要生成的表名
            //                            .addInclude("tbl_user") // 设置需要生成的表名
            //                            .addTablePrefix("tbl_"); // 设置过滤表前缀
            
            // 开启 lombok 模型
            builder.entityBuilder().enableLombok();
            builder.serviceBuilder()
                    // 设置service的命名策略,没有这个配置的话，生成的service和serviceImpl类前面会有一个I，比如IUserService和IUserServiceImpl
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl");
            builder.controllerBuilder()
                    // 开启生成@RestController 控制器，不配置这个默认是Controller注解，RestController是返回Json字符串的，多用于前后端分离项目。
                    .enableRestStyle();
            builder.mapperBuilder()
                    //开启 @Mapper 注解，也就是在dao接口上添加一个@Mapper注解，这个注解的作用是开启注解模式，就可以在接口的抽象方法上面直接使用@Select和@Insert和@Update和@Delete注解。
                    .enableMapperAnnotation();
        });
    }
    
    /**
     * 模板配置(TemplateConfig)
     */
    public static void templateConfig(FastAutoGenerator generator) {
        // .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        generator
                .templateEngine(new VelocityTemplateEngine())
                .templateConfig(builder -> {
                    builder.controller("/templates/TemplateController.ftlh.vm");
                });
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        generate();
    }
}
