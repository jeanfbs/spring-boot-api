FROM java:openjdk-8u111-alpine

LABEL source="https://github.com/jeanfbs/spring-webflux" \
      maintainer="jeanfbs" \
      author="jeanfbs" \
      slack="#slack-team"

ADD target/*.jar /opt/app.jar

ENTRYPOINT ["java","-jar","/opt/app.jar"]
