# maidsTask
LibrarySystem


# Let's write the provided documentation into a Markdown (.md) file.

documentation_content = """
# Library Management System Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Running the Application](#running-the-application)
3. [Interacting with API Endpoints](#interacting-with-api-endpoints)
   - [Book Management](#book-management)
   - [Patron Management](#patron-management)
   - [Borrowing and Returning Books](#borrowing-and-returning-books)
4. [Authentication and Authorization](#authentication-and-authorization)
   - [JWT Token Usage](#jwt-token-usage)
5. [Error Handling](#error-handling)
6. [Logging](#logging)
7. [Testing](#testing)

## Introduction

The Library Management System is a Spring Boot-based application designed to manage various library operations, including book management, patron management, and the borrowing/returning of books. The application uses RESTful APIs and incorporates security through JWT authentication.

## Running the Application

### Prerequisites

Before running the application, ensure that the following are installed:

- Java 17
- Maven 3.8+
- Docker (for database container)
- PostgreSQL (if not using Docker)
- IDE (IntelliJ IDEA or similar)

### Steps to Run

1. **Clone the Repository:**

   \`\`\`bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   \`\`\`

2. **Set Up the Database:**

   - **Using Docker:**

     Run the following command to start a PostgreSQL container:

     \`\`\`bash
     docker run --name library-db -e POSTGRES_USER=maids -e POSTGRES_PASSWORD=maidsPass -e POSTGRES_DB=library -p 5432:5432 -d postgres
     \`\`\`

   - **Manual Setup:**

     Set up a PostgreSQL database with the following details:

     - **Database Name:** \`library\`
     - **User:** \`maids\`
     - **Password:** \`maidsPass\`

3. **Configure Application Properties:**

   Update the \`application.properties\` file located in \`src/main/resources/\` with your database credentials:

   \`\`\`properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/library
   spring.datasource.username=maids
   spring.datasource.password=maidsPass
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   \`\`\`

4. **Build and Run the Application:**

   Execute the following Maven commands to build and run the application:

   \`\`\`bash
   mvn clean install
   mvn spring-boot:run
   \`\`\`

5. **Access the Application:**

   The application will run on \`http://localhost:8080\`.

## Interacting with API Endpoints

### Book Management

- **POST /api/books**
  - **Description:** Add a new book.
  - **Request Body:**
    \`\`\`json
    {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "isbn": "9780743273565",
      "publishedDate": "1925-04-10"
    }
    \`\`\`
  - **Response:** Returns the newly created book object.

- **GET /api/books/{id}**
  - **Description:** Retrieve details of a specific book by its ID.
  - **Response:** Returns the book object.

- **PUT /api/books/{id}**
  - **Description:** Update an existing book’s information.
  - **Request Body:**
    \`\`\`json
    {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "isbn": "9780743273565",
      "publishedDate": "1925-04-10"
    }
    \`\`\`
  - **Response:** Returns the updated book object.

- **DELETE /api/books/{id}**
  - **Description:** Delete a book by its ID.
  - **Response:** Returns a success message.

### Patron Management

- **POST /api/patrons**
  - **Description:** Register a new patron.
  - **Request Body:**
    \`\`\`json
    {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "membershipDate": "2024-01-01"
    }
    \`\`\`
  - **Response:** Returns the newly created patron object.

- **GET /api/patrons/{id}**
  - **Description:** Retrieve details of a specific patron by their ID.
  - **Response:** Returns the patron object.

- **PUT /api/patrons/{id}**
  - **Description:** Update an existing patron's information.
  - **Request Body:**
    \`\`\`json
    {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "membershipDate": "2024-01-01"
    }
    \`\`\`
  - **Response:** Returns the updated patron object.

- **DELETE /api/patrons/{id}**
  - **Description:** Delete a patron by their ID.
  - **Response:** Returns a success message.

### Borrowing and Returning Books

- **POST /api/borrow/{bookId}/patron/{patronId}**
  - **Description:** Record the borrowing of a book by a patron.
  - **Response:** Returns a confirmation message.

- **PUT /api/return/{bookId}/patron/{patronId}**
  - **Description:** Record the return of a borrowed book by a patron.
  - **Response:** Returns a confirmation message.

## Authentication and Authorization

### JWT Token Usage

- **Authentication:** The application uses JWT tokens for securing API endpoints. Upon successful login, the user receives a JWT token, which must be included in the \`Authorization\` header for subsequent requests.

  \`\`\`http
  Authorization: Bearer <JWT_TOKEN>
  \`\`\`

- **Roles:** The application supports role-based access control, primarily distinguishing between \`Admin\` and \`Patron\` roles.

### Example Login Request

- **POST /api/auth/login**
  - **Request Body:**
    \`\`\`json
    {
      "username": "admin",
      "password": "adminPass"
    }
    \`\`\`
  - **Response:** Returns a JWT token.

## Error Handling

The application uses custom error handling to return meaningful messages. Errors are returned with appropriate HTTP status codes and JSON payloads, e.g.:

- **400 Bad Request:** Validation errors or incorrect data format.
- **404 Not Found:** Resource not found.
- **500 Internal Server Error:** General server error.

Example Error Response:

\`\`\`json
{
  "timestamp": "2024-08-14T08:55:10.251+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Book not found with id 1",
  "path": "/api/books/1"
}
\`\`\`

## Logging

- **Aspect-Oriented Logging:** The application uses AOP to log requests and responses, providing centralized logging for all API interactions.
- **Log Levels:** Various log levels (\`INFO\`, \`DEBUG\`, \`ERROR\`) are used to categorize logs.

## Testing

### Running Tests

To run the tests, use the following Maven command:

\`\`\`bash
mvn test
\`\`\`

### Test Coverage

- **Unit Tests:** Cover service layers and individual components.
- **Integration Tests:** Test the interaction between different components and the database.
"""

# Save the content to a README.md file
file_path = "/mnt/data/README.md"
with open(file_path, "w") as file:
    file.write(documentation_content)

file_path
