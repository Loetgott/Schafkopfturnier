import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.formdev.flatlaf.*;


public class Gui {
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
        mainPanel.add(new Tisch());
        mainFrame.setVisible(true);
        System.out.println("mainFrame generated!");

        JFrame configFrame = new JFrame("");
        configFrame.setUndecorated(false);
        configFrame.setSize(new Dimension(1920,1080));
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel configPanel = new JPanel(new GridBagLayout());
        configFrame.add(configPanel);
        configFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].getDefaultConfiguration().getBounds().getLocation());
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("test");
        JMenu test = new JMenu("test1");
        menuBar.add(menu);
        menuBar.add(test);
        configFrame.setJMenuBar(menuBar);
        configFrame.setVisible(true);
        System.out.println("configFrame generated!");
    }

}
