FROM openjdk:17.0.1-jdk-slim
EXPOSE 8080
ADD /build/libs/student-management-system.jar student-management-system.jar
ENTRYPOINT ["java","-jar", "/student-management-system.jar"]