FROM java:8

VOLUME /tmp

COPY studentmanager-0.0.1-SNAPSHOT.jar app.jar

RUN bash -c "touch /app.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=test", "--server.port=8080", "> /log/app.log"]