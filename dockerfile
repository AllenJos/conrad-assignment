FROM openjdk:11
LABEL maintainer "codeworkshop"
ADD build/libs/gs-spring-boot-0.1.0.jar gs-spring-boot-0.1.0.jar
ENTRYPOINT ["java","-jar","gs-spring-boot-0.1.0.jar"]