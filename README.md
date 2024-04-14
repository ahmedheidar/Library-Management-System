
# Library Management System API Documentation

Welcome to the documentation for the Library Management System API developed using Spring Boot. This guide provides comprehensive instructions on setting up, running, and utilizing the API endpoints for managing books, patrons, and borrowing records.

## Table of Contents

1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Configuration](#configuration)
4. [Running the Application](#running-the-application)
5. [API Documentation](#api-documentation)
6. [Entity Structure](#entity-structure)
7. [Testing](#testing)
8. [Deployment](#deployment)

---

## 1. Introduction <a name="introduction"></a>

The Library Management System API is designed to facilitate the management of library resources including books, patrons, and borrowing records. It provides a set of RESTful endpoints for performing CRUD operations on these entities.

Key Features:
- Book management (add, retrieve, update, delete)
- Patron management (add, retrieve, update, delete)
- Borrowing operations (borrow, return)

## 2. Getting Started <a name="getting-started"></a>

To get started with the Library Management System API, follow these steps:

### Prerequisites
- Java Development Kit (JDK) installed (version 8 or higher)
- Apache Maven or Gradle build tool installed

### Clone the Repository
```bash
git clone <repository_url>
cd library-management-system
```

## 3. Configuration <a name="configuration"></a>

### Database Configuration
The application uses an embedded H2 database by default. If you want to use a different database (e.g., MySQL, PostgreSQL), update the `application.properties` file accordingly.

```properties
spring.datasource.url=jdbc:mysql://localhost:5432/library_db
spring.datasource.username=db_user
spring.datasource.password=db_password
spring.jpa.hibernate.ddl-auto=update
```

## 4. Running the Application <a name="running-the-application"></a>

### Using Maven
```bash
mvn clean package
mvn spring-boot:run
```

### Using Gradle
```bash
./gradlew build
./gradlew bootRun
```

## 5. API Documentation <a name="api-documentation"></a>

### Endpoints

#### Books
- `GET /api/books`: Retrieve all books
- `GET /api/books/{id}`: Retrieve a specific book by ID
- `POST /api/books`: Add a new book
- `PUT /api/books/{id}`: Update an existing book
- `DELETE /api/books/{id}`: Remove a book

#### Patrons
- `GET /api/patrons`: Retrieve all patrons
- `GET /api/patrons/{id}`: Retrieve a specific patron by ID
- `POST /api/patrons`: Add a new patron
- `PUT /api/patrons/{id}`: Update an existing patron
- `DELETE /api/patrons/{id}`: Remove a patron

#### Borrowing
- `POST /api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book
- `PUT /api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book
- `GET /api/borrowed-books`: Retrieve all borrowed books

For detailed request and response formats, refer to the API documentation.

## 6. Entity Structure <a name="entity-structure"></a>

The Library Management System API uses the following entities:

- **Book**: Represents a library book with attributes like title, author, publication year, etc.
- **Patron**: Represents a library patron with details such as name, contact information, etc.
- **Borrowing Record**: Tracks the borrowing history of books by patrons.

## 7. Testing <a name="testing"></a>

Unit tests and integration tests are available to validate the functionality of the API endpoints. Use the following command to run the tests:

```bash
mvn test   # For Maven
./gradlew test  # For Gradle
```

## 8. Deployment <a name="deployment"></a>

For production deployment, build the application and run the packaged JAR file:

```bash
mvn clean package
java -jar target/library-management-system.jar
```

---


Thank you for using our Library Management System API! 

