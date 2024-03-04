FROM openjdk:17-alpine3.14
COPY /build/libs/micro-search-0.0.1.jar /micro-search.jar
CMD ["java", "-jar", "/micro-search.jar"]
