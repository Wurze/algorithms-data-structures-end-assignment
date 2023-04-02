import DataStructures.ArrayListDS;
import DataStructures.LinkedListDS;
import DataStructures.TreeMapDS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class AlgorithmDurationGUI extends JFrame implements ActionListener {
    private DatasetProcessor<String,Book> bookProcessor;
    private final JTextArea outputArea;
    private final JComboBox<String> dataStructureComboBox;
    private final JComboBox<String> algorithmComboBox;
    private final JButton runButton;

    public AlgorithmDurationGUI() {
        setTitle("Algorithms Duration GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load data button
        JButton loadDataButton = new JButton("Load Books Data");
        loadDataButton.addActionListener(e -> {
            loadDataButton.setEnabled(false);

            LinkedListDS<String, Book> linkedListTable = new LinkedListDS<>();
            ArrayListDS<String, Book> arrayListTable = new ArrayListDS<>();
            TreeMapDS<String, Book> treeMapTable = new TreeMapDS<>();



            bookProcessor = new DatasetProcessor<>(linkedListTable, arrayListTable, treeMapTable);
            loadDataButton.setEnabled(true);
        });
        add(loadDataButton, BorderLayout.NORTH);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Algorithm and data structure selection panel
        JPanel selectionPanel = new JPanel(new GridLayout(1, 2));

        // Data structure selection combo box
        dataStructureComboBox = new JComboBox<>();
        dataStructureComboBox.addItem("LinkedList");
        dataStructureComboBox.addItem("ArrayList");
        dataStructureComboBox.addItem("TreeMap");
        selectionPanel.add(dataStructureComboBox);

        // Algorithm selection combo box
        algorithmComboBox = new JComboBox<>();
        algorithmComboBox.addItem("Sort by title");
        algorithmComboBox.addItem("Search book by title");
        algorithmComboBox.addItem("Sort by natural order");
        algorithmComboBox.addItem("Search books by author");
        selectionPanel.add(algorithmComboBox);

        add(selectionPanel, BorderLayout.SOUTH);

        // Execute algorithm button
        runButton = new JButton("Execute Algorithm");
        runButton.addActionListener(this);
        add(runButton, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            runButton.setEnabled(false);
            AlgorithmWorker worker = new AlgorithmWorker(dataStructureComboBox.getSelectedIndex(),
                    algorithmComboBox.getSelectedIndex());
            worker.execute();
        }
    }

    private class AlgorithmWorker extends SwingWorker<Void, String> {
        private final int dataStructureIndex;
        private final int algorithmIndex;
        public AlgorithmWorker(int dataStructureIndex, int algorithmIndex) {
            this.dataStructureIndex = dataStructureIndex;
            this.algorithmIndex = algorithmIndex;

        }

        @Override
        protected Void doInBackground() {
            long startTime, endTime;
            if (bookProcessor == null) {
                publish("Please load books data before executing an algorithm.");
                return null;
            }

            switch (dataStructureIndex) {
                case 0 -> publish("Using LinkedList data structure.");
                case 1 -> publish("Using ArrayList data structure.");
                case 2 -> publish("Using TreeMap data structure.");
                default -> {
                    publish("Invalid data structure selection.");
                    return null;
                }
            }


            switch (algorithmIndex) {
                case 0 -> {
                    // Algorithm 1: Sort books by title
                    startTime = System.currentTimeMillis();
                    List<Book> sortedBooks = bookProcessor.sortItems(Comparator.comparing(Book::getTitle), dataStructureIndex);
                    endTime = System.currentTimeMillis();
                    publish("Algorithm 1 (Sort by title) duration: " + (endTime - startTime) + " ms");
                    publish("First 5 sorted books by title: ");
                    for (int i = 0; i < 5 && i < sortedBooks.size(); i++) {
                        publish(sortedBooks.get(i).getTitle());
                    }
                }
                case 1 -> {
                    // Algorithm 2: Search book by title
                    startTime = System.currentTimeMillis();
                    String title = "In a Sunburned Country";
                    Optional<Book> book = bookProcessor.searchItemByCriteria(b -> b.getTitle().equals(title), dataStructureIndex);
                    endTime = System.currentTimeMillis();
                    publish("Algorithm 2 (Search book by title) duration: " + (endTime - startTime) + " ms");
                    if (book.isPresent()) {
                        publish("Book found: " + book.get().getTitle());
                    } else {
                        publish("Book not found.");
                    }
                }
                case 2 -> {
                    // Algorithm 3: Sort books by natural order
                    startTime = System.currentTimeMillis();
                    List<Book> sortedBooksByNaturalOrder = bookProcessor.sortItemsByNaturalOrder(dataStructureIndex);
                    endTime = System.currentTimeMillis();
                    publish("Algorithm 3 (Sort by natural order) duration: " + (endTime - startTime) + " ms");
                    publish("First 5 sorted books by natural order: ");
                    for (int i = 0; i < 5 && i < sortedBooksByNaturalOrder.size(); i++) {
                        publish(sortedBooksByNaturalOrder.get(i).getTitle());
                    }
                }
                case 3 -> {
                    // Algorithm 4: Search books by author
                    startTime = System.currentTimeMillis();
                    String author = "Bill Bryson";
                    List<Book> booksByAuthor = bookProcessor.searchItemsByFieldValue(Book::getAuthor, author, dataStructureIndex);
                    endTime = System.currentTimeMillis();
                    publish("Algorithm 4 (Search books by author) duration: " + (endTime - startTime) + " ms");
                    publish("Books found by author: " + author);
                    for (Book bookByAuthor : booksByAuthor) {
                        publish(bookByAuthor.getTitle());
                    }
                }
                default -> publish("Invalid algorithm selection.");
            }
            return null;
        }

        @Override
        protected void process(List<String> outputMessages) {
            for (String message : outputMessages) {
                outputArea.append(message + "\n");
            }
        }

        @Override
        protected void done() {
            runButton.setEnabled(true);
        }


    }


}
