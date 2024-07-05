public class Book {
    private int id;
    private String title;
    private String author;
    private boolean borrowed;
    private String borrowedBy;

    public Book(int id, String title, String author, boolean borrowed, String borrowedBy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
        this.borrowedBy = borrowedBy;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrowed=" + borrowed +
                ", borrowedBy='" + borrowedBy + '\'' +
                '}';
    }
}
