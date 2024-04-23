# Spring Boot 3 JWT Authentication Example
This repository contains a Spring Boot 3 application that demonstrates the implementation of JWT (JSON Web Token) authentication using Spring Security 6. The application features a RESTful API secured with JWT, including different access levels for admin, user, and guest roles. Swagger UI is integrated for easy testing and interaction with the API.

## Features
- JWT Authentication: Secure API endpoints using stateless, scalable JWT authentication.
- Role-Based Access Control: Three roles implemented:
- Admin: Access to admin-specific endpoints.
- User: Access to user profile management and personal data.
- Guest: Access to general, non-sensitive endpoints.
- Swagger UI Integration: Easy-to-use interface for real-time API testing and documentation.
  
## API Endpoints:
- User Controller: Registration and login functionalities.
- Admin Controller: Admin-specific functionalities.
- Guest Controller: Basic public APIs accessible without authentication.
  
## Technology Stack
- Spring Boot 3: Framework for building stand-alone, production-grade Spring based applications.
- Spring Security 6: Authentication and access-control framework highly customizable to meet enterprise requirements.
- Springdoc OpenAPI: Swagger OpenAPI 3 integration for Spring Boot applications.
- Getting Started
  
## Prerequisites
- JDK 11+
- Maven 3.6+
- Running the Application
- Clone the repository:
- bash
  
### Copy code
git clone https://github.com/your-username/your-repository-name.git
Navigate to the project directory:
bash
Copy code
cd your-repository-name
Run the application:
bash
Copy code
mvn spring-boot:run
Accessing Swagger UI
Once the application is running, visit Swagger UI to interact with the API.

## Security Enhancements

### Prevention of IDOR (Insecure Direct Object References) Attacks
In the latest update(v3), we have introduced robust mechanisms to prevent IDOR attacks. IDOR vulnerabilities allow attackers to access unauthorized data by modifying direct object references used in requests. To mitigate this risk, we have implemented additional checks to validate that the authenticated user has the necessary permissions to access the requested resources.

### Contributing
* Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change.
