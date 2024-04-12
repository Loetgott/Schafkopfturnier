import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.*;

public class MultiplePanelsExample {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame frame = new JFrame("Multiple Panels Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Erstellen von Panels mit unterschiedlichen Layouts
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.RED);
        panel1.add(new JLabel("Panel 1 - BorderLayout.CENTER"), BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.GREEN);
        panel2.add(new JLabel("Panel 2 - FlowLayout"));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 2));
        panel3.setBackground(Color.BLUE);
        panel3.add(new JLabel("Panel 3 - GridLayout (2x2)"));
        panel3.add(new JButton("Button 1"));
        panel3.add(new JButton("Button 2"));
        panel3.add(new JButton("Button 3"));

        // Ein Hauptpanel erstellen, um die Unterpanels zu halten
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1)); // 2 Zeilen, 1 Spalte
        leftPanel.add(panel1);
        leftPanel.add(panel2);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // 1 Zeile, 2 Spalten
        mainPanel.add(leftPanel);
        mainPanel.add(panel3);

        // Hauptpanel zum JFrame hinzufügen
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
