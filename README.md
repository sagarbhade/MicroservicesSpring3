# MicroservicesSpring3
Demo Project developed to use microservice architecture on spring boot 3

There are 2 different projects are there in the repository.
1. To show the use of spring cloud config. and use 2 microservices.
   1. limits-service which has simple logic to fetch the current minimum and maximum limits defined in the properties.
   2. spring-cloud-config fetch the properties from application.properties file which is hosted on github or from local
      storage.

2. This project is for the demo fo restTemplate, OpenFeign, Eureka naming server, Spring API gateway and Resilience4j.
   i. currency exchange which has the logic to fetch conversion rate of different currencies and uses H2 database.
   ii. currency conversion service is used to convert one denomination to other and internally uses the currency exchange
     service to fetch the current exchange rates of the 2 currencies to convert.
   iii. api-gateway is used implement spring API gateway to access all other microservices through this gateway microservice
     to implement some common functionality in this project we implemented the logging feature.
   iv. naming-server has config for Eureka server to manage all the instance of all microservices to help implementing
     load balancing through openfeign.
     
Steps to Run.
To run 2nd project you need to first run the naming server to properly run all other services as other services has 
dependency on naming server.
