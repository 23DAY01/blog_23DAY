# 基于java镜像创建新镜像
FROM openjdk:8
# 作者
MAINTAINER 23DAY
# 将jar包添加到容器中并更名为app.jar
ADD  ./blog_23DAY.jar /var/www/blog_23DAY.jar
# 运行jar包
ENTRYPOINT ["nohup","java","-jar","/var/www/blog_23DAY.jar","&"]
