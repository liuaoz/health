FROM openjdk:14
EXPOSE 8080
MAINTAINER cxx "576729125@qq.com"
VOLUME /tmp
ADD ./target/health.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]