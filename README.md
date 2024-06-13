  # Simple Online Bookstore

Its a RESTful API for a simple online bookstore

## Setup Instructions

1. **Clone the repository**:
    ```bash
    git clone <https://github.com/Chrysz-Ojo/simple-online-bookStore>
    cd simple-online-bookstore
    ```

2. **Build the project**:
    ```bash
    ./mvnw clean install
    ```

3. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## Design Explanation

This project is a simple online bookstore implemented using Spring Boot and JPA. It includes the following functionalities:
- Viewing a list of available books.
- Adding new books to the store.
- Updating book details.
- Deleting books from the store.
- Managing the availability status of books.

## API Documentation

### Get All Books
- **URL**: `/api/books`
- **Method**: `GET`
- **Response**: `200 OK`, list of books

### Get Book By ID
- **URL**: `/api/books/{id}`
- **Method**: `GET`
- **Response**: `200 OK`, book details or `404 Not Found` if the book doesn't exist

### Add New Book
- **URL**: `/api/books`
- **Method**: `POST`
- **Request Body**: JSON representation of the book
- **Response**: `201 Created`, created book details

### Update Book
- **URL**: `/api/books/{id}`
- **Method**: `PUT`
- **Request Body**: JSON representation of the updated book details
- **Response**: `200 OK`, updated book details

### Delete Book
- **URL**: `/api/books/{id}`
- **Method**: `DELETE`
- **Response**: `204 No Content` or `404 Not Found` if the book doesn't exist

### Check Availability
- **URL**: `/api/books/{id}/availability`
- **Method**: `GET`
- **Response**: `200 OK`, availability status of the book or `404 Not Found` if the book doesn't exist

### Update Availability
- **URL**: `/api/books/{id}/availability`
- **Method**: `PUT`
- **Request Param**: `available` (boolean)
- **Response**: `200 OK`, updated book details

## Error Handling

- **404 Not Found**: Returned when the requested resource is not found.
- **400 Bad Request**: Returned for invalid input data.

## Authentication

Basic authentication is used for securing the API endpoints. The default username and password are:

- **Username**: `user`
- **Password**: `password`
