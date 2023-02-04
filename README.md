<p align=center>
  <a href="https://www.blog.23day.site">
    <img src="https://static.23day.site/config/1e21dd23d2d32d3.png" alt="23DAY简易开发模板" style="border-radius: 50%" weight="250px" height="250px">
  </a>
</p>
<p align=center>
   基于 springboot + springsecurity 的博客后端 code 
</p>

<p align="center">
   <a target="_blank" href="https://github.com/23DAY01/template_23DAY">
      <img src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
      <img src="https://img.shields.io/badge/springboot-2.7.4.RELEASE-green"/>
      <img src="https://img.shields.io/badge/mysql-8.0.20-green"/>
      <img src="https://img.shields.io/badge/knife4j-2.0.7-green"/>
      <img src="https://img.shields.io/badge/mybatis--plus-3.4.0-green"/>
      <img src="https://img.shields.io/badge/redis-6.0.5-green"/>
      <img src="https://img.shields.io/badge/springsecurity-2.7.4-green"/>
   </a>
</p>

## 写在前面

笔记：https://www.blog.23day.site/articles/82

这是一篇记录如何从建仓开始到最后安全测试完整流程的笔记，使用的`spring`生态，目的是为`spring`的基础后端开发及后期渗透测试打一个模板。本篇采用springSecurity作为安全框架，搭载了`redis-cache`、`spring-valid`等功能，并开放了OAuth2授权登录。

本篇代码格式规范为**优雅**，代码大量使用`java8`新特性以及`apache`包下方便的工具类，能一行代码写完的就不用两行。当然本文也配备了大量注释，方便有需要的同学学习。

本篇中对于springSecurity的拓展有点繁琐，建议大家读一下springSecurity的源码或者自己debug一下它的处理流程再尝试自己拓展，否则会出现各种bug。

笔记概览：

<img src="https://blog-23day.oss-cn-hangzhou.aliyuncs.com/articles/1b2f324c90037413515d5b8b2d9622f0.png" alt="image.png" style="zoom: 67%;" />

## 技术栈
- springBoot
- docker-compose
- springSecurity
- swagger
- mybatisPlus
- springCache
- log4j+logback
- mapStruct
- springSession
- ……


## TODO

- [ ] 采用`MQ延时队列`实现动态的控制定时任务
- [x] 对请求数据进行注入检测
- [x] 服务器性能检测
- [ ] 利用工具对系统进行渗透测试
- [ ] 优化文章导入策略
- [ ] 完善业务逻辑
- [ ] 加入Elasticsearch
- [ ] 容灾备份

## 目录结构

```
site.day.template
├── annotation    			    --  自定义注解
├── aspect        			    --  aop模块
├── config        			    --  配置模块
│       └── property                        --  配置属性
├── constant      			    --  常量模块
├── controller    			    --  控制器模块
├── enums         			    --  枚举模块
├── exception     			    --  自定义异常模块
├── filter     			            --  过滤器模块
│       └── wrapper                         --  增强器
├── handler       			    --  处理器模块
│       └── securityHandler                 --  springSecurity拓展处理器
├── intercept       			    --  拦截器模块
├── listener                                --  监听器
├── mapper       			    --  数据库操作模块
├── pojo       			            --  实体类模块
│       └── domain                 
│       └── dto                 
│       └── vo    
├── server                                  --  服务器监控模块
├── service       			    --  服务模块
│       └── impl                            --  服务实现类模块
├── strategy      			    --  策略模块
└── utils         			    --  工具类模块
```

