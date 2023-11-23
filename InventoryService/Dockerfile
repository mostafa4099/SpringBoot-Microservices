FROM openjdk:17-oracle
EXPOSE 8181
ADD target/InventoryService.jar InventoryService.jar
ENTRYPOINT ["java","-jar","/InventoryService.jar"]