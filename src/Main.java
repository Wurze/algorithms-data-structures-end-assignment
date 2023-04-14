import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmDurationGUI gui = new AlgorithmDurationGUI();
            gui.setVisible(true);
        });
    }
}
