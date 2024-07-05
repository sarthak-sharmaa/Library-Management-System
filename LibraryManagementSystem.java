import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem {
    private JFrame frame;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField studentIdField;
    private JTextField newStudentIdField;
    private JTextField newStudentNameField;
    private JTextArea displayArea;
    private Library library;

    public LibraryManagementSystem() {
        library = new Library();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Title : ");
        titleField = new JTextField();
        titleField.setColumns(30);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);

        JPanel authorPanel = new JPanel(new BorderLayout());
        JLabel authorLabel = new JLabel("Author : ");
        authorField = new JTextField();
        authorField.setColumns(30);
        authorPanel.add(authorLabel, BorderLayout.WEST);
        authorPanel.add(authorField, BorderLayout.CENTER);

        JPanel studentIdPanel = new JPanel(new BorderLayout());
        JLabel studentIdLabel = new JLabel("Student ID : ");
        studentIdField = new JTextField();
        studentIdField.setColumns(10);
        studentIdPanel.add(studentIdLabel, BorderLayout.WEST);
        studentIdPanel.add(studentIdField, BorderLayout.CENTER);

        JPanel newStudentPanel = new JPanel(new BorderLayout());
        JLabel newStudentIdLabel = new JLabel("New Student ID : ");
        newStudentIdField = new JTextField();
        newStudentIdField.setColumns(10);
        newStudentPanel.add(newStudentIdLabel, BorderLayout.WEST);
        newStudentPanel.add(newStudentIdField, BorderLayout.CENTER);

        JPanel newStudentNamePanel = new JPanel(new BorderLayout());
        JLabel newStudentNameLabel = new JLabel("New Student Name : ");
        newStudentNameField = new JTextField();
        newStudentNameField.setColumns(20);
        newStudentNamePanel.add(newStudentNameLabel, BorderLayout.WEST);
        newStudentNamePanel.add(newStudentNameField, BorderLayout.CENTER);

        inputPanel.add(titlePanel);
        inputPanel.add(authorPanel);
        inputPanel.add(studentIdPanel);
        inputPanel.add(newStudentPanel);
        inputPanel.add(newStudentNamePanel);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                if (title.isEmpty() || author.isEmpty()) {
                    displayArea.setText("Title and Author fields cannot be empty.");
                    return;
                }
                Book book = new Book(0, title, author, false, null);
                library.addBook(book);
                displayArea.setText("Book added: " + book.getTitle());
                resetFields();
            }
        });

        JButton searchButton = new JButton("Search Book");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                if (title.isEmpty()) {
                    displayArea.setText("Please enter a title to search.");
                    return;
                }
                Book book = library.searchBook(title);
                if (book != null) {
                    displayArea.setText("Book found: " + book);
                } else {
                    displayArea.setText("Book not found.");
                }
                resetFields();
            }
        });

        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String studentId = studentIdField.getText();
                if (title.isEmpty() || studentId.isEmpty()) {
                    displayArea.setText("Title and Student ID fields cannot be empty.");
                    return;
                }
                Book book = library.borrowBook(title, studentId);
                if (book != null) {
                    displayArea.setText("Book borrowed: " + book.getTitle() + " by Student ID: " + studentId);
                } else {
                    displayArea.setText("Book not available or invalid Student ID.");
                }
                resetFields();
            }
        });

        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                if (title.isEmpty()) {
                    displayArea.setText("Please enter a title to return.");
                    return;
                }
                Book book = library.returnBook(title);
                if (book != null) {
                    displayArea.setText("Book returned: " + book.getTitle());
                } else {
                    displayArea.setText("Book not found or not borrowed.");
                }
                resetFields();
            }
        });

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentId = newStudentIdField.getText();
                String studentName = newStudentNameField.getText();
                if (studentId.isEmpty() || studentName.isEmpty()) {
                    displayArea.setText("Student ID and Student Name fields cannot be empty.");
                    return;
                }
                Student student = new Student(0, studentId, studentName);
                library.addStudent(student);
                displayArea.setText("Student added: " + student.getStudentId());
                resetFields();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(addStudentButton);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void resetFields() {
        titleField.setText("");
        authorField.setText("");
        studentIdField.setText("");
        newStudentIdField.setText("");
        newStudentNameField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementSystem();
            }
        });
    }
}
