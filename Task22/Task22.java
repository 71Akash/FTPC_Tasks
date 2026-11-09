import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

class Book {
    private final int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void updateDetails(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: \"" + title + "\" | Author: " + author
                + " | Status: " + (available ? "Available" : "Issued");
    }
}

class BookNotFoundException extends Exception {
    public BookNotFoundException(int id) {
        super("No book found with ID " + id + ".");
    }
}

public class Task22 {
    private final List<Book> books = new ArrayList<>();
    private final Path storageFile = Paths.get("books.txt");
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Task22 library = new Task22();
        try {
            library.loadBooks();
            library.runMenu();
        } catch (IOException e) {
            System.out.println("Could not load or save books: " + e.getMessage());
        } finally {
            library.scanner.close();
        }
    }

    private void runMenu() throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book       2. Delete Book    3. Update Book");
            System.out.println("4. Search         5. Issue Book     6. Return Book");
            System.out.println("7. List All       8. List Available 9. Exit");
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1: addBook(); break;
                case 2: deleteBook(); break;
                case 3: updateBook(); break;
                case 4: searchBooks(); break;
                case 5: issueBook(); break;
                case 6: returnBook(); break;
                case 7: printBooks(books); break;
                case 8: listAvailableBooks(); break;
                case 9:
                    saveBooks();
                    System.out.println("Books saved to " + storageFile + ". Goodbye.");
                    running = false;
                    break;
                default: System.out.println("Invalid menu option.");
            }
        }
    }

    private void addBook() {
        int id = readInt("Enter book id: ");
        if (findBook(id).isPresent()) {
            System.out.println("A book with that ID already exists.");
            return;
        }
        String title = readText("Enter title: ");
        String author = readText("Enter author: ");
        books.add(new Book(id, title, author, true));
        System.out.println("Book added.");
    }

    private void deleteBook() {
        int id = readInt("Enter book id to delete: ");
        try {
            Book book = requireBook(id);
            books.remove(book);
            System.out.println("Book deleted.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateBook() {
        int id = readInt("Enter book id to update: ");
        try {
            Book book = requireBook(id);
            String title = readText("Enter new title: ");
            String author = readText("Enter new author: ");
            book.updateDetails(title, author);
            System.out.println("Book updated.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchBooks() {
        String query = readText("Search title or author: ").toLowerCase();
        List<Book> matches = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query)
                        || book.getAuthor().toLowerCase().contains(query))
                .collect(Collectors.toList());
        if (matches.isEmpty()) System.out.println("No matching books found.");
        else printBooks(matches);
    }

    private void issueBook() {
        int id = readInt("Enter book id: ");
        try {
            Book book = requireBook(id);
            if (!book.isAvailable()) {
                System.out.println("Book \"" + book.getTitle() + "\" is already issued.");
                return;
            }
            book.setAvailable(false);
            System.out.println("Book \"" + book.getTitle() + "\" issued successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void returnBook() {
        int id = readInt("Enter book id: ");
        try {
            Book book = requireBook(id);
            if (book.isAvailable()) {
                System.out.println("Book \"" + book.getTitle() + "\" is already available.");
                return;
            }
            book.setAvailable(true);
            System.out.println("Book \"" + book.getTitle() + "\" returned successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listAvailableBooks() {
        List<Book> availableBooks = books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
        printBooks(availableBooks);
    }

    private void printBooks(List<Book> booksToPrint) {
        if (booksToPrint.isEmpty()) {
            System.out.println("No books to display.");
            return;
        }
        booksToPrint.forEach(System.out::println);
    }

    // Optional example: findBook returns an Optional instead of null.
    private Optional<Book> findBook(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }

    private Book requireBook(int id) throws BookNotFoundException {
        return findBook(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void loadBooks() throws IOException {
        if (!Files.exists(storageFile)) return;
        int skippedLines = 0;
        for (String line : Files.readAllLines(storageFile, StandardCharsets.UTF_8)) {
            if (line.trim().isEmpty()) continue;
            try {
                String[] fields = line.split("\\|", -1);
                if (fields.length != 4) throw new IllegalArgumentException("Invalid record");
                int id = Integer.parseInt(fields[0]);
                String title = decode(fields[2]);
                String author = decode(fields[3]);
                boolean available = Boolean.parseBoolean(fields[1]);
                books.add(new Book(id, title, author, available));
            } catch (IllegalArgumentException e) {
                skippedLines++;
            }
        }
        if (skippedLines > 0) {
            System.out.println("Skipped " + skippedLines + " invalid record(s) in " + storageFile + ".");
        }
    }

    private void saveBooks() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Book book : books) {
            lines.add(book.getId() + "|" + book.isAvailable() + "|"
                    + encode(book.getTitle()) + "|" + encode(book.getAuthor()));
        }
        Files.write(storageFile, lines, StandardCharsets.UTF_8);
    }

    private String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }
}
