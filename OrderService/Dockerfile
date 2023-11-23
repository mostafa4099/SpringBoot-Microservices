FROM openjdk:17-oracle
EXPOSE 8181
ADD target/OrderService.jar OrderService.jar
ENTRYPOINT ["java","-jar","/OrderService.jar"]