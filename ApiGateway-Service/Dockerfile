FROM openjdk:17-oracle
EXPOSE 8181
ADD target/ApiGatewayService.jar ApiGatewayService.jar
ENTRYPOINT ["java","-jar","/ApiGatewayService.jar"]