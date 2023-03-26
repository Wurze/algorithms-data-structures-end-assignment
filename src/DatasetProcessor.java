import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatasetProcessor {
    private String booksDataset;
    private List<Book> booksData;
    private HashMap<String, List<Book>> booksByAuthor;
    private HashMap<Double, List<Book>> booksByRating;

    public DatasetProcessor(String booksDataset) {
        this.booksDataset = booksDataset;
        booksData = new ArrayList<>();
        booksByAuthor = new HashMap<>();
        booksByRating = new HashMap<>();
        loadData();
    }

    private void loadData() {
        InputStream booksStream = ClassLoader.getSystemResourceAsStream(booksDataset);
        if (booksStream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(booksStream))) {
                String line;
                br.readLine(); // Skip header

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

                while ((line = br.readLine()) != null) {
                    try {
                        String[] values = line.split(",");
                        Date date = dateFormat.parse(values[3]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        int publicationYear = calendar.get(Calendar.YEAR);
                        Book book = new Book(values[0], values[1], Double.parseDouble(values[2]), publicationYear);
                        booksData.add(book);

                        // Add book to booksByAuthor HashMap
                        booksByAuthor.computeIfAbsent(book.getAuthor(), k -> new ArrayList<>()).add(book);

                        // Add book to booksByRating HashMap
                        booksByRating.computeIfAbsent(book.getRating(), k -> new ArrayList<>()).add(book);
                    } catch (Exception e) {
                        System.err.println("Error while parsing line: " + line);
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File not found: " + booksDataset);
        }
    }

    public List<Book> getBooksData() {
        return booksData;
    }

    // Get a book by its index in the dataset
    public Book getBook(int index) {
        if (index >= 0 && index < booksData.size()) {
            return booksData.get(index);
        }
        return null;
    }
    public List<Book> sortBooksByTitle() {
        List<Book> sortedBooks = new ArrayList<>(booksData);
        sortedBooks.sort(Comparator.comparing(Book::getTitle));
        return sortedBooks;
    }

    public Optional<Book> searchBookByTitle(String title) {
        List<Book> sortedBooks = sortBooksByTitle();
        int index = Collections.binarySearch(sortedBooks, new Book(title, "", 0.0, 0), Comparator.comparing(Book::getTitle));
        return (index >= 0) ? Optional.of(sortedBooks.get(index)) : Optional.empty();
    }

    // Delete a book by its index in the dataset
    public void deleteBook(int index) {
        if (index >= 0 && index < booksData.size()) {
            Book book = booksData.remove(index);

            // Remove the book from booksByAuthor HashMap
            List<Book> authorBooks = booksByAuthor.get(book.getAuthor());
            authorBooks.remove(book);
            if (authorBooks.isEmpty()) {
                booksByAuthor.remove(book.getAuthor());
            }

            // Remove the book from booksByRating HashMap
            List<Book> ratingBooks = booksByRating.get(book.getRating());
            ratingBooks.remove(book);
            if (ratingBooks.isEmpty()) {
                booksByRating.remove(book.getRating());
            }
        }
    }

    // Update a book at a specified index with a new book
    public void updateBook(int index, Book newBook) {
        if (index >= 0 && index < booksData.size()) {
            Book oldBook = booksData.set(index, newBook);

            // Update booksByAuthor HashMap
            booksByAuthor.get(oldBook.getAuthor()).remove(oldBook);
            booksByAuthor.computeIfAbsent(newBook.getAuthor(), k -> new ArrayList<>()).add(newBook);

            // Update booksByRating HashMap
            booksByRating.get(oldBook.getRating()).remove(oldBook);
            booksByRating.computeIfAbsent(newBook.getRating(), k -> new ArrayList<>()).add(newBook);
        }
    }

}
