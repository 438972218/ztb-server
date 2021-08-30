# base image
FROM registry.cn-shenzhen.aliyuncs.com/a852203465/alpine-jdk8:slim

ARG MODULE_NAME

# MAINTAINER
MAINTAINER rong.jia@xdcplus.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# put jar into container
ADD $MODULE_NAME/target/*.jar app.jar

# running required command
ENTRYPOINT ["/bin/sh", "-c", "set -e && java -Xms2048m -Xmx4096m -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8 -jar /app.jar"]

