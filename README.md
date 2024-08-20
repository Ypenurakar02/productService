# Product Service API

This is a Spring Boot microservice for managing products. It provides RESTful endpoints to perform CRUD operations on a Product entity.

## Features

- Create, Read, Update, and Delete Products
- Database integration with JPA and H2 (or MySQL/PostgreSQL, if configured)
- RESTful API design following best practices
- Error handling and logging
- Unit testing with JUnit and Mockito
- API documentation with Swagger UI

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven 3.6+ or Gradle (depending on your build tool preference)

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone <repository-URL>
   cd product-service

2. **Build the project:**
     If you are using Maven:
       mvn clean install
     Or, if using Gradle:
       ./gradlew build
3. **Run the application:**
     If you are using Maven:
       mvn spring-boot:run
     Or, if using Gradle:
       ./gradlew bootRun
3. **Access the application:**
     The service will be available at http://localhost:8081
       mvn spring-boot:run
     Swagger UI for API documentation will be available at:
       http://localhost:8081/swagger-ui/index.html
       

### Running the Test

**Execute unit tests**
  If you are using Maven:
    mvn test
  Or, if using Gradle:
    ./gradlew test
    
### Configurtaion
**Database Configuration:**
By default, the application uses an embedded H2 database. To switch to another database like MySQL or PostgreSQL, modify the application.properties file in the src/main/resources directory.
**Swagger Configuration:**
Swagger is enabled by default and can be accessed at the /swagger-ui/index.html endpoint.

### Endpoints
GET    : /api/products: Retrieve all products

GET    : /api/products/{id}: Retrieve a specific product by ID

POST   : /api/products: Create a new product
### Create Product

- **Endpoint:** `POST /api/products`
- **Description:** Create a new product.

#### Request

**Headers:**
- `Content-Type: application/json`

**Body:**
```json
{
  "name": "Sample Product",
  "description": "This is a sample product",
  "price": 19.99,
  "createdAt": "2024-08-21T10:00:00",
  "updatedAt": "2024-08-21T10:00:00"
}```

PUT    : /api/products/{id}: Update an existing product by ID

DELETE : /api/products/{id}: Delete a product by ID


### Additional Notes
**Error Handling:**
The application provides basic error handling with meaningful HTTP status codes.

**Logging:**
Logging is configured using SLF4J and Logback. You can customize the logging level in the application.properties file.

**OpenAPI Specification:**
The OpenAPI specification for the API can be found at /v3/api-docs.

