import java.sql.*;

public class Library {
    private Connection conn;

    public Library() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "root_password");
            System.out.println("Connection with SQL Database established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book searchBook(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getBoolean("borrowed"),
                        resultSet.getString("borrowed_by"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book borrowBook(String title, String studentId) {
        Book book = searchBook(title);
        if (book != null && !book.isBorrowed()) {
            String sql = "UPDATE books SET borrowed = true, borrowed_by = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, studentId);
                statement.setInt(2, book.getId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    book.setBorrowed(true);
                    book.setBorrowedBy(studentId);
                    return book;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Book returnBook(String title) {
        Book book = searchBook(title);
        if (book != null && book.isBorrowed()) {
            String sql = "UPDATE books SET borrowed = false, borrowed_by = NULL WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, book.getId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    book.setBorrowed(false);
                    book.setBorrowedBy(null);
                    return book;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, student_name) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getStudentName());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Library library = new Library();
//        // Your GUI initialization or command-line interface can be implemented here
//    }
}
