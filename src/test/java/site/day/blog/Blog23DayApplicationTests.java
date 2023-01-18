package site.day.blog;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.*;

@SpringBootTest
class Blog23DayApplicationTests {

    public String author = "23DAY";
    public String database = "day_blog";
    //父包名
    public String parent = "site.day.blog";

    private static class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
        @Override
        protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
            String entityName = tableInfo.getEntityName();
            String otherPath = this.getPathInfo(OutputFile.other);
            customFile.forEach((key, value) -> {
                String fileName = otherPath + File.separator + key.toLowerCase() + File.separator + entityName + key + ".java";
                this.outputFile(new File(fileName), objectMap, value);
            });
        }
    }

    @Test
    void contextLoads() {

        List<String> s = new ArrayList<>();
        s.add("day_article");
        s.add("day_article_tag");
        s.add("day_category");
        s.add("day_chat_record");
        s.add("day_comment");
        s.add("day_friend_link");
        s.add("day_menu");
        s.add("day_message");
        s.add("day_operation_log");
        s.add("day_page");
        s.add("day_resource");
        s.add("day_role");
        s.add("day_role_menu");
        s.add("day_role_resource");
        s.add("day_tag");
        s.add("day_talk");
        s.add("day_view");
        s.add("day_user_auth");
        s.add("day_user_info");
        s.add("day_user_role");
        s.add("day_website_config");

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/" + database + "?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "20010823GTH")
                .globalConfig(builder -> {
                    builder.disableOpenDir() //禁止打开输出目录
//                            .fileOverride()
                            .outputDir("./src/main/java")// 指定输出目录
                            .author(author)// 设置作者
                            .enableSwagger()// 开启swagger
                            .dateType(DateType.TIME_PACK)
                            //.commentDate("yyyy-MM-dd")
                            .commentDate("yyyy/MM/dd HH:mm")
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .entity("pojo.domain")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapperxml")
                            .controller("controller")
                            .other("pojo")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/mapper"))//文件的输出路径
                            .build();
                })
                .templateConfig(builder -> {
                    builder.entity("/templates/domain.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .controller("/templates/controller.java")
                            .build();
                })
                .strategyConfig(builder -> {
                    builder.addInclude(s)
                            .addTablePrefix("day_")
                            .entityBuilder()
//                            .disableSerialVersionUID()//禁用生成serialVersionUID
                            .enableLombok()
                            .enableChainModel()
//							.enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .idType(IdType.ASSIGN_ID)//主键策略
                            .formatFileName("%s")//文件名
                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .build();
                })
                .injectionConfig(builder -> {
                    builder.customFile(new HashMap<String, String>() {{
                                put("DTO", "/templates/dto.java.ftl");
                                put("VO", "/templates/vo.java.ftl");
                            }})
                            // 自定义键值对 ${}
                            .customMap(new HashMap<String, Object>() {{
                                put("basePath", parent);
                            }})
                            .build();
                })
                //.templateEngine(new EnhanceFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new EnhanceFreemarkerTemplateEngine())  // 使用增强FreeMaker引擎模板，增加dto、vo输出
                .execute();

    }

    @Test
    public void fn() {
        System.out.println(1);
    }

}
