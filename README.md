# Library-Management-System
The Library Management System is a Java-based application designed to manage the operations of a library. It has features for adding new books and students, finding books, checking out books, and returning books. The system stores book and student information in a MySQL database. A GUI made using Swing, enables easy access.
## Features

- **Add Book**: Add a new book to the library's collection.
- **Search Book**: Search for a book by its title.
- **Borrow Book**: Borrow a book from the library using the student's ID.
- **Return Book**: Return a borrowed book to the library.
- **Add Student**: Add a new student to the library's database.

## Requirements

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J (JDBC Driver for MySQL)

- ## Database Setup

1. Install MySQL Server and MySQL Workbench.
2. Create a new database called `library_db`.
3. Create the `books` table:

    ```sql
    CREATE TABLE books (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL,
        borrowed BOOLEAN DEFAULT FALSE,
        borrowed_by VARCHAR(255)
    );
    ```

4. Create the `students` table:

    ```sql
    CREATE TABLE students (
        id INT AUTO_INCREMENT PRIMARY KEY,
        student_id VARCHAR(255) NOT NULL,
        student_name VARCHAR(255) NOT NULL
    );
    ```

