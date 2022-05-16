FROM openjdk:14
MAINTAINER cxx "576729125@qq.com"
VOLUME /tmp
WORKDIR /opt/
#COPY /root/config/health/application.yml /opt/prod/application.yml
ADD ./target/health.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.config.location=/data/config/application.yml","-jar","/app.jar"]