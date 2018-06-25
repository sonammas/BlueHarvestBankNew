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
