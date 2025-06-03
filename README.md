# Spring JWT Demo

A Spring Boot application demonstrating JWT-based authentication and authorization using Spring Security OAuth2 Resource Server.

## Overview

This project showcases how to implement secure JWT (JSON Web Token) authentication in a Spring Boot application. It provides a robust security layer for REST APIs with token-based authentication.

## Features

- JWT-based authentication and authorization
- Spring Security integration
- Token generation and validation
- Secure API endpoints
- MySQL database integration with Spring Data JPA
- Input validation using Spring Validation

## Prerequisites

- Java 17
- Maven
- MySQL database

## Dependencies

- Spring Boot 3.5.0
- Spring Data JPA
- Spring Security OAuth2 Resource Server
- Spring Validation
- Spring Web
- MySQL Connector

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/learnwithiftekhar/spring-jwt-with-spring-security.git
cd spring-jwt-with-spring-security
```

### 2. Configure database

Edit the `application.properties` or `application.yml` file to configure your MySQL database connection:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure JWT properties

Add JWT configuration in your application properties file:

```properties
# JWT Secret key for signing tokens
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=your_jwk_set_uri
# OR for symmetric key
security.jwt.secret=your_secret_key
```

### 4. Build and run the application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on port 8080 by default.

## API Usage

1. **Authenticate** to receive a JWT token
2. **Include the token** in the Authorization header for subsequent requests:
   ```
   Authorization: Bearer your_jwt_token
   ```

## Security Implementation

The project uses Spring Security with OAuth2 Resource Server to implement JWT-based authentication:

- JwtService: Handles token generation with configurable expiration
- Custom authentication flow with user details from the database
- Role-based authorization

## Development

### Building from source

```bash
mvn clean install
```

### Running tests

```bash
mvn test
```

## Author

Iftekhar Hossain

[My YouTube Channel](https://www.youtube.com/@learnWithIfte)

[LinkedIn](https://www.linkedin.com/in/hossain-md-iftekhar/)

