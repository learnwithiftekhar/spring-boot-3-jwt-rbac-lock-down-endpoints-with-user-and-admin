# Spring Boot 3 JWT RBAC — Lock Down Endpoints with User & Admin Roles

A Spring Boot application demonstrating JWT-based authentication and role-based access control (RBAC) using Spring Security OAuth2 Resource Server. This project showcases how to secure REST API endpoints with different access levels for regular users and administrators, using enterprise-grade RSA key pairs and JWKS (JSON Web Key Set) for JWT token security.

This project uses RSA key pairs for signing and verifying JWT tokens. You can generate your own keys using OpenSSL:

```bash
# 1. Generate a 2048-bit RSA *private* key (PKCS #8 -- Nimbus & Spring accept this as-is)
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048

# 2. Derive the matching *public* key
openssl rsa -pubout -in private.pem -out public.pem
```

Place the generated `private.pem` and `public.pem` files in `src/main/resources/certs/` and reference them in your configuration.

> **Note:**
> - The provided RSA key files (`private.pem` and `public.pem`) are for demonstration purposes only. **Do not use these keys in production.**
> - Every developer must generate their own unique RSA key pair for any real-world or production use. See the instructions below for generating your own keys.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Development](#development)
- [Testing](#testing)
- [License](#license)
- [Author](#author)

## Related YouTube Tutorials
- [Spring Boot 3: Secure Your API with HS256 JWTs (Part 1)](https://www.youtube.com/watch?v=nX66hgUcmS0)
- [RSA & JWKS in Spring Boot 3: Secure JWT the Enterprise Way (Part 2)](https://youtu.be/1-Bf7nrLSds)
- [Spring Boot 3 JWT RBAC — Lock Down Endpoints with User & Admin Roles (Part 3)](https://youtu.be/aDEkqNcSzuA)

## Overview
This project demonstrates how to implement JWT-based authentication with Role-Based Access Control (RBAC) in a Spring Boot application. It provides a comprehensive security solution that differentiates between regular users and administrators, ensuring that sensitive endpoints are properly protected. The application leverages RSA key pairs and JWKS for enterprise-level JWT token security, while implementing fine-grained access control based on user roles.

## Features
- **Role-Based Access Control (RBAC)**: Differentiate between USER and ADMIN roles with fine-grained permissions
- **JWT-based Authentication**: Secure token-based authentication and authorization
- **Protected Endpoints**: Lock down sensitive admin endpoints while allowing user access to appropriate resources
- **Spring Security Integration**: Comprehensive security configuration with OAuth2 Resource Server
- **Token Generation and Validation**: RSA-based JWT token creation and verification
- **MySQL Database Integration**: User and role management with Spring Data JPA
- **Input Validation**: Request validation using Spring Validation
- **RSA Key Pair Support**: Enterprise-grade JWT signing and verification with OpenSSL key generation
- **JWKS Ready Configuration**: Scalable public key distribution for microservices architecture

## Project Structure
```
src/main/java/com/learnwithiftekhar/spring_jwt_demo/
├── config/           # Security configuration with RBAC
├── controller/       # REST controllers (Auth, Home, Admin)
├── dto/              # Data Transfer Objects (LoginRequest, LoginResponse)
├── model/            # JPA entities (User, Role)
├── ropository/       # Spring Data JPA repositories (User, Role)
├── service/          # Business logic (JWT, User)
src/main/resources/
├── application.properties  # Main configuration
├── certs/                  # RSA keys for JWT
```

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
git clone https://github.com/learnwithiftekhar/spring-boot-3-jwt-rbac-lock-down-endpoints-with-user-and-admin.git
cd spring-boot-3-jwt-rbac-lock-down-endpoints-with-user-and-admin
```

### 2. Configure database
Edit the `src/main/resources/application.properties` file to configure your MySQL database connection:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<your_database>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure JWT keys

Generate RSA key pair for JWT signing and verification:

```bash
# 1. Generate a 2048-bit RSA *private* key (PKCS #8 -- Nimbus & Spring accept this as-is)
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048

# 2. Derive the matching *public* key
openssl rsa -pubout -in private.pem -out public.pem
```

Place the generated `private.pem` and `public.pem` files in `src/main/resources/certs/`.

**Note:** This RSA key is for demonstration only. Do not use in production.

Reference these in your `application.properties`:
```properties
security.jwt.private-key=classpath:certs/private.pem
security.jwt.public-key=classpath:certs/public.pem
```

### 4. Build and run the application
```bash
mvn clean install
mvn spring-boot:run
```
The application will start on port 8080 by default.

## API Endpoints

### Public Endpoints
- `POST /api/v1/auth/login`  
  **Access**: Public (no authentication required)  
  **Request**: `{ "username": "admin", "password": "admin" }`  
  **Response**: `{ "token": "<JWT_TOKEN>" }`

### User Endpoints (Authenticated Users)
- `GET /api/v1/hello`  
  **Access**: Any authenticated user (USER or ADMIN role)  
  **Requires**: `Authorization: Bearer <JWT_TOKEN>`  
  **Response**: `Hello <username>`

### Admin Endpoints (Admin Only)
- `GET /api/v1/admin/get-all-users`  
  **Access**: ADMIN role only  
  **Requires**: `Authorization: Bearer <JWT_TOKEN>` with ADMIN role  
  **Response**: List of all users in the system

## Configuration
- Database and JWT key configuration is in `src/main/resources/application.properties`.
- **Default Users**: The application creates default users on startup with different roles:
  - Admin user: `admin` / `admin` (with ADMIN role)
  - Regular users can be created through the application with USER role
- **Role-Based Access**: 
  - ADMIN role: Access to all endpoints including `/api/v1/admin/**`
  - USER role: Access to general authenticated endpoints like `/api/v1/hello`
- **Token Configuration**: JWT token expiration is set to 15 minutes (configurable in `JwtService`).

## Development
- To build:  
  `mvn clean install`
- To run:  
  `mvn spring-boot:run`

## Testing
- To run tests:  
  `mvn test`

## License
This project is for educational/demo purposes.

## Author
Iftekhar Hossain  
Email: learnwithiftekhar@gmail.com

[My YouTube Channel](https://www.youtube.com/@learnWithIfte)

[LinkedIn](https://www.linkedin.com/in/hossain-md-iftekhar/)
