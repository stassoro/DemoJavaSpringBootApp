# Basic Spring Boot REST API Example

This is a simple Spring Boot application that exposes a REST API with two endpoints:
- `/customers`.
- `/books`.

This project is built using Maven and the Spring Boot framework.

## Features
- A basic REST API with two endpoints that implement CRUD functionality.
- Data is returned as JSON.
- Support for 2 types of DB: 
  - In-memory database "H2" (no external database required).
  - Postgres database.

## Prerequisites

Before running this project, make sure you have the following installed:

- **Java 17** (for compiling the project).
- **Maven** (for building and running the project).
- **Spring Boot** (embedded within the project).

## Getting Started

To run this project locally, follow these steps:

### 1. Clone the repository

```
git clone https://github.com/yourusername/DemoJavaSpringBootApp.git
cd DemoJavaSpringBootApp
```

### 2. Build the project
Run the following Maven command to build the project:
```
mvn clean install
```

### 3. change the config
The config file is an application.properties in the following path: `src\main\resources\application.properties`
There are 2 profiles:
 1. Dev - a development config that uses H2 database
 2. Prod - a production config that uses Postgres database, to use it update the following file `src\main\resources\application-prod.properties`

### 4. Run the application
Run the Spring Boot application using the following command:
```
mvn spring-boot:run
```
The application will start on http://localhost:8080 by default.

### 5. Access the API
Once the application is running, you can access diff endpoints:

1. GET `/customers`: get a list of customers.
1. GET `/customers/{id}`: get a specific customer.
2. POST `/customers`: create a new customer.
3. GET `/books`: Returns a list of books.
4. POST `/books`: create a new books.
5. GET `/books/{id}`: get a specific books.
6. DELETE `/books/{id}`: delete a specific books.

Example Response for /customers:

`curl -X GET http://localhost:8080/books`
```
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  },
  {
    "id": 2,
    "name": "Jane Doe",
    "email": "jane@example.com"
  }
]
```
### 6. run tests

use `mvn test` to run the project tests