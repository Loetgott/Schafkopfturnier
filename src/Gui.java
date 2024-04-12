import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    public static DefaultListModel<String> playerListModel = new DefaultListModel<>();
    public static JList<String> playerList = new JList<>(playerListModel);

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

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        JFrame configFrame = new JFrame("");
        configFrame.setUndecorated(false);
        configFrame.setSize(new Dimension(800, 600));
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel playerPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel(new GridBagLayout());
        JPanel listPanel = new JPanel(new BorderLayout()); // Use BorderLayout
        JPanel rightPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Abstand zwischen den Komponenten

        JLabel nameLabel = new JLabel("Nachname");
        JLabel vornameLabel = new JLabel("Vorname");
        gbc.gridx = 0;
        addPanel.add(vornameLabel,gbc);
        gbc.gridx ++;
        addPanel.add(nameLabel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;

        JTextField vorname = new JTextField();
        addPanel.add(vorname, gbc);
        gbc.gridx ++;
        JTextField name = new JTextField();
        addPanel.add(name, gbc);
        gbc.gridx ++;
        JButton saveButton = new JButton("save");
        vorname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !vorname.getText().isEmpty()){
                    name.requestFocus();
                }
            }
        });
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !vorname.getText().isEmpty()){
                    if(!vorname.getText().isBlank() && !name.getText().isBlank()){
                        Game.addPlayer(vorname.getText(),name.getText());
                        vorname.setText("");
                        name.setText("");
                        vorname.requestFocus();
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if ( e.getKeyCode() == KeyEvent.VK_SPACE && !vorname.getText().isEmpty()) {
                    if (!vorname.getText().isBlank() && !name.getText().isBlank()) {
                        Game.addPlayer(vorname.getText(), name.getText());
                        vorname.setText("");
                        name.setText("");
                        vorname.requestFocus();
                    }
                }
            }

        });
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!vorname.getText().isBlank() && !name.getText().isBlank()){
                    Game.addPlayer(vorname.getText(),name.getText());
                    vorname.setText("");
                    name.setText("");
                    vorname.requestFocus();
                }
            }
        });
        saveButton.setFocusable(false);
        addPanel.add(saveButton, gbc);

        playerList.setSelectionForeground(Color.WHITE);
        playerList.setSelectionBackground(new Color(116, 116, 121));
        playerList.setBackground(configFrame.getBackground());
        playerList.setForeground(Color.WHITE);
        playerList.setFont(configFrame.getFont());
        JScrollPane listScrollPane = new JScrollPane(playerList);
        listScrollPane.setFocusable(false);
        playerList.setFocusable(false);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        playerPanel.add(addPanel, BorderLayout.NORTH);
        playerPanel.add(listPanel, BorderLayout.CENTER);
        configFrame.add(playerPanel, BorderLayout.WEST);

        JButton changeNameButton = new JButton("change Name");
        changeNameButton.setFocusable(false);
        changeNameButton.setEnabled(false);
        changeNameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);//----------------------------------------------------hier weitermachen
            }
        });
        rightPanel.add(changeNameButton);
        configFrame.add(rightPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu playerMenu = new JMenu("Spieler");
        JMenu test = new JMenu("test1");
        menuBar.add(playerMenu);
        menuBar.add(test);
        configFrame.setJMenuBar(menuBar);

        configFrame.setLocationRelativeTo(null);
        configFrame.setVisible(true);
        System.out.println(GREEN + "configFrame generated!" + RESET);
    }

    public static void addPlayerToList(Player nPlayer){
        playerListModel.addElement(nPlayer.getName());
    }
}