# Task 22: Library Management

## Run the application

Open a terminal in this `Task22` folder, then run:

```text
javac Task22.java
java Task22
```

The application loads `books.txt` at startup and saves the book list when you choose **9. Exit**. Keep the terminal's working directory in this folder so the data file is stored here. A missing `books.txt` is normal on the first run; it will be created on exit.

The menu supports adding, deleting, updating, searching by title or author, issuing and returning books, listing all books, and listing only available books. Search uses streams and lambdas. Book lookup returns an `Optional`, and a missing ID raises the checked `BookNotFoundException`.

Each data row in `books.txt` stores the book ID, availability, and Base64-encoded UTF-8 title and author. Encoding keeps delimiters and other punctuation in book details safe when saving and loading.

## Bonus: producer-consumer threads

```text
javac ProducerConsumerDemo.java
java ProducerConsumerDemo
```

The demo uses a `LinkedBlockingQueue`: one thread produces book titles and another consumes them.
