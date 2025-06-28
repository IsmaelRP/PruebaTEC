# Shop
Technical test of e-commerce on Spring Boot

## Architecture
A Ports and Adapters (Hexagonal) Architecture was implemented, including the corresponding Infrastructure, Application, and Domain layers.
In addition, Vertical Slicing was applied to separate business entities.
A CQRS (Command Query Responsibility Segregation) architecture is also in use to separate queries and commands (currently only queries are implemented).

## Dependencies
Lombok was used to generate cleaner and more organized code.
Tomcat was used as the server and JDBC drivers for connecting to the in-memory H2 database.
The application is built with Spring Boot 3.5.3 and Java 21.

## Security
IMPORTANT:
Spring Security was used to restrict access to the API.
Specifically, a Bearer Token–based authorization is required, where the token must be obtained via the login endpoint.
All API calls must include this token to be authorized.

## Endpoints (port 8080)
The first endpoint is mandatory to access the rest, since the Bearer token returned must be added to the authentication settings in Postman (if testing from Postman), so all other endpoints inherit it and become accessible.

1º Obtain Bearer token for authorization
(Token expires in 10 days and is globally unique for the app, for simplicity)
Payload parameters: user, password
	Payload parameters: user, password
http://localhost:8080/users/login

2º Get a price
	URL parameters: application date, product ID, brand ID
http://localhost:8080/prices/findPrice



## Testing

### Unit Testing
Mockito and JUnit 5 were used.
Achieved 100% coverage in the controller and service layers (ObtainPriceUseCase and PricesController).

### Integration Testing
MockMVC was used to test against the H2 database and validate the 5 scenarios described in the assignment.

### Swagger
Swagger UI is available at:
http://localhost:8080/swagger-ui/index.html

### Postman
A .json Postman collection is included with multiple requests to test:
- The 5 required test cases
- Login to obtain the Bearer token
- Various possible error scenarios

