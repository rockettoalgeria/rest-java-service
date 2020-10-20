# rest-java-service

Simple Spring Boot service with PostgreSQL database, listening for RabbitMQ messages.

<h2>API:</h2>

    - GET "/"                  just for testing connection.
    - GET "/contact"           returns list of objects in PostgrSQL db.
    - GET "/contact={id}"      returns {id} object description.
    - POST "/contact"          add new object to db. You must send { "name": "<objectname>" } with request.
    - PUT "/contact={id}"      update object.
    - DELETE "/contact={id}"   delete {id} object.

<h2>Configuration:</h2>
First of all, you need to start RabbitMQ and PostgreSQL servers running on localhost:5430 and :5672 respectively. Also init db "employees" with "contact" table. <br/>
    * If RabbitMQ works very slow or you have "Socket Closed" error while running service, try to use docker-compose.yml (RabbitMQ from dockerhub)

<h2>How to run:</h2>

  - You can use rabbitmq-message-sender for communicate with Spring Boot service:
      - Compile with < javac -cp amqp-client-5.7.1.jar Sender.java >
      - Run with < java -cp .:amqp-client-5.7.1.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar Sender "YourMessageHere" >
  - Run main service with ./mvnw spring-boot:run in springboot_service/ directory.
  
 <h3> Main service port is :8081</h3>
