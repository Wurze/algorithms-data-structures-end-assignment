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
}
