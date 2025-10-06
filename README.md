# BlossomTest
Blossom test

##Instructions:
1. Open a cmd console on the root folder of the project.
2. Execute the command mvn clean install -U
3. Go to the target folder and run **java -jar jarfilename.jar** to deploy the application where jarfilename is the name of the jar file present in /target folder. Replace It by the real file jar name.
4. Go to the Postman workspace and execute some rest endpoints
5. For see the the rest endpoints go to this url for http://localhost:8080/swagger-ui/index.html
6. For executing unit tests open the cmd console in the project root folder and execute **mvn test**

##Architectural decisions:
This microservice uses MVC pattern for consuming API endpoints through a controller that calls a service

Uses builder pattern to build objects in a fast way uses the Builder design pattern

Uses the repository pattern thorugh the JpaRepository child interfaces

Uses the strategy pattern through the class PaymentStrategyServiceImpl that get an implementation of PaymentService to process the payment of an user order depending of the paymentMethod value. The payment methods are CARD and CASH.

Uses Mappers for mapping between entites and dtos using mapstruct library