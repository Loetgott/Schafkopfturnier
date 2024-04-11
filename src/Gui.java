import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.formdev.flatlaf.*;


public class Gui {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public Gui() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame mainFrame = new JFrame("Plan");
        mainFrame.setSize(new Dimension(1920,1080));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setUndecorated(true);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainFrame.add(mainPanel);
        mainFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().getLocation());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setVisible(true);
        mainFrame.setVisible(true);
        System.out.println(GREEN + "mainFrame generated!" + RESET);

        JFrame configFrame = new JFrame("");
        configFrame.setUndecorated(false);
        configFrame.setSize(new Dimension(1920,1080));
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel playerPanel = new JPanel(new GridBagLayout());
        configFrame.add(playerPanel);
        configFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].getDefaultConfiguration().getBounds().getLocation());
        JMenuBar menuBar = new JMenuBar();
        JMenu playerMenu = new JMenu("Spieler");
        JMenu test = new JMenu("test1");
        menuBar.add(playerMenu);
        menuBar.add(test);
        configFrame.setJMenuBar(menuBar);
        configFrame.setVisible(true);
        System.out.println(GREEN + "configFrame generated!" + RESET);
    }
}
