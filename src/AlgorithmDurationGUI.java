import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
public class AlgorithmDurationGUI extends JFrame implements ActionListener {
    private JButton runButton;
    private JTextArea outputArea;
    private DatasetProcessor datasetProcessor;

    public AlgorithmDurationGUI() {
        setTitle("Algorithm Duration");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        runButton = new JButton("Run Algorithms");
        runButton.addActionListener(this);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        setLayout(new BorderLayout());
        add(runButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        datasetProcessor = new DatasetProcessor("dataset/books.csv");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            runButton.setEnabled(false);

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() {
                    long startTime, endTime;

                    // Algorithm 1: Sort books by title
                    startTime = System.currentTimeMillis();
                    List<Book> sortedBooks = datasetProcessor.sortBooksByTitle();
                    endTime = System.currentTimeMillis();
                    outputArea.append("Algorithm 1 (Sort by title) duration: " + (endTime - startTime) + " ms\n");
                    outputArea.append("First 5 sorted books by title: \n");
                    for (int i = 0; i < 5 && i < sortedBooks.size(); i++) {
                        outputArea.append(sortedBooks.get(i).getTitle() + "\n");
                    }

                    // Algorithm 2: Search book by title
                    startTime = System.currentTimeMillis();
                    String title = "In a Sunburned Country";
                    Optional<Book> book = datasetProcessor.searchBookByTitle(title);
                    endTime = System.currentTimeMillis();
                    outputArea.append("Algorithm 2 (Search book by title) duration: " + (endTime - startTime) + " ms\n");
                    book.ifPresent(b -> outputArea.append("Book found: " + b.getTitle() + "\n"));

                    // Algorithm 3: Average rating by author
                    startTime = System.currentTimeMillis();
                    String author = "Bill Bryson";
                    double averageRating = datasetProcessor.averageRatingByAuthor(author);
                    endTime = System.currentTimeMillis();
                    outputArea.append("Algorithm 3 (Average rating by author) duration: " + (endTime - startTime) + " ms\n");
                    outputArea.append("Average rating for author '" + author + "': " + averageRating + "\n");

                    // Algorithm 4: Find books with specific rating
                    startTime = System.currentTimeMillis();
                    double rating = 4.5;
                    List<Book> booksWithRating = datasetProcessor.findBooksWithRating(rating);
                    endTime = System.currentTimeMillis();
                    outputArea.append("Algorithm 4 (Find books with specific rating) duration: " + (endTime - startTime) + " ms\n");
                    outputArea.append("Books with " + rating + " rating: \n");
                    for (Book b : booksWithRating) {
                        outputArea.append(b.getTitle() + "\n");
                    }

                    return null;
                }

                @Override
                protected void done() {
                    outputArea.append("All algorithms completed.\n");
                    runButton.setEnabled(true);
                }
            };
            worker.execute();
    }
}
}
