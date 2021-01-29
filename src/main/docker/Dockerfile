FROM openjdk:11-jre-slim
VOLUME /tmp
COPY ./mydata.ucapp.webservice.jar mydata.ucapp.webservice.jar
RUN sh -c 'touch /mydata.ucapp.webservice.jar'
EXPOSE 9552
ENTRYPOINT [ "sh", "-c", "java -Dfile.encoding=utf-8 -Djava.security.egd=file:/dev/./urandom -jar /mydata.ucapp.webservice.jar" ]
