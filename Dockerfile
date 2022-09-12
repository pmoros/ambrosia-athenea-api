FROM gradle:7.5-jdk8
WORKDIR /.
COPY . .
ENV GRADLE_HOME=/app/gradle-7.5
ENV PATH=$PATH:$GRADLE_HOME/bin

EXPOSE 8080
CMD ["bash", "gradlew", "bootRun"]