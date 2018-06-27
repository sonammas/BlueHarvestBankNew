# Blue Harvest Bank Microservice
This is microservice based application. It uses few of the microservice concepts.
Angular is used for the front end. Below are more details.

# Application
This application has 4 parts.

1. Accounts :- This is a spring boot based microservice which exposes 2 rest end points.
First to open the secondary bank account for the existing customers. It uses h2 as database.
Second is to fetch all the transactions for any customer along with his name, surname and balance on the account.
It also registers with the eureka naming server so that it becomes the discoverable service. It has unit and integration tests. 
Awesome !!

2. eureka-server :- In microservices based systems all the service should be discoverable. Eureka can be used for this purpose.
Stock inventory and api gateway registers to the eureka.

3. Transactions :- This is a spring boot based microservice which exposes 2 rest end points.
It also registers with the eureka naming server so that it becomes the discoverable service. 
This microservice will be called by Accounts microservice to create any transaction between any two accounts. It uses resttemplate. Account service just need to know the name of this service and does not have to know anything about the port information. This is the beauty of Eureka Server Discovery Client !!

4. zuul-api-gateway-server :- This is common pattern in microservices based systems where all the requests to the microservices
should go through api gateways. Hence now all the clients just knows the api gateway whitout
knowing the hosts details of the stock-inventory services. Now we can run as many instances
of stock-inventory service which registers to eureka server and api gateway uses
Ribbon to load balance all the requests from all the clients.
Fantastic !!!

# Implementation
Below technologies or concepts are used

Spring boot,
Java 8,
Spring,
Microservices based concepts like gateways , naming server,
Hibernate/JPA,
Rest concepts


# Deployment

./gradlew bootRun for all the backend services. Run the eureka server first ie. netflix-eureka-naming-server.

Ports: - For the backend services check the server.port in application properties.
DataLoader.java is a class which is loading test data. This will create 2 Customers. Also it will create one primary account for one customer

# Prerequiste software
Java 8
Postman

# How to Run
Use postman to call the rest end points.
> To open secondary account - http://localhost:8823/account/open/customerId/1/initialCredit/10

> To get all the transactions - http://localhost:8823/customer/1/transactions/type/PRIMARY

