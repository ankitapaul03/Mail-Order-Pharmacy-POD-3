Following are the URL's for accessing the h2-console,Swagger API documentation and Angular frontend.

Frontend -> Angular: http://localhost:4200/

Backend ->Microservices

1. Authorization:
    --- db: http://localhost:8090/authapp/h2-console
    --- Swagger: http://localhost:8090/authapp/swagger-ui.html
    --- spring.datasource.url: jdbc:h2:mem:authdb
    --- username: sa

2. Drugs:
    --- db: http://localhost:8081/drugdetailapp/h2-console
    --- Swagger: http://localhost:8081/drugdetailapp/swagger-ui.html
    --- spring.datasource.url: jdbc:h2:mem:drugsdb
    --- username: sa

3. Subscription:
    --- db: http://localhost:8082/subscriptionapp/h2-console
    --- Swagger: http://localhost:8082/subscriptionapp/swagger-ui.html
    --- spring.datasource.url: jdbc:h2:mem:subscriptiondb
    --- username: sa

4. Refill:
    --- db: http://localhost:8454/refillapp/h2-console
    --- Swagger: http://localhost:8454/refillapp/swagger-ui.html
    --- spring.datasource.url: jdbc:h2:mem:refilldb
    --- username: sa

