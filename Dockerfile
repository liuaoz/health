FROM java:11
EXPOSE 9000
VOLUME /tmp
ADD ./target/health.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]