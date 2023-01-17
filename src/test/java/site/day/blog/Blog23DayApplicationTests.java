package site.day.blog;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class Blog23DayApplicationTests {

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

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/day_blog?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "20010823GTH")
                .globalConfig(builder -> {
                    builder.disableOpenDir() //禁止打开输出目录
//                            .fileOverride()
                            .outputDir("./src/main/java")// 指定输出目录
                            .author("23DAY")// 设置作者
                            .enableSwagger()// 开启swagger
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("site.day.blog") // 设置父包名
                            .entity("pojo.domain")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapperxml")
                            .controller("controller")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/mapper"))//文件的输出路径
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
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    @Test
    public void fn(){
        System.out.println(1);
    }

}
