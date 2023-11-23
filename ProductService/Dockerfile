FROM openjdk:17-oracle
EXPOSE 8181
ADD target/ProductService.jar ProductService.jar
ENTRYPOINT ["java","-jar","/ProductService.jar"]