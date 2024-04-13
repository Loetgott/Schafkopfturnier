import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

import com.formdev.flatlaf.*;

public class Gui {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
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

        //ab hier alles Main configPanel
        JPanel mainConfigPanel =new JPanel(new BorderLayout());
        JButton button2 = new JButton();
        button2.setVisible(true);
        mainConfigPanel.add(button2,BorderLayout.NORTH);
        mainConfigPanel.setVisible(true);

        //ab hier alles Player Panel
        JPanel playerAddPanel = new JPanel(new BorderLayout());
        playerAddPanel.setVisible(false);
        JPanel addPanel = new JPanel(new GridBagLayout());
        JPanel listPanel = new JPanel(new BorderLayout()); // Use BorderLayout
        JPanel changePanel = new JPanel(new GridLayout(3,1));
        JPanel changePlayerNamePanel = new JPanel(new GridBagLayout());
        JPanel changePlayerPointsPanel = new JPanel(new GridBagLayout());
        JPanel changePlayerTisch = new JPanel(new GridBagLayout());
        changePlayerPointsPanel.setVisible(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 2, 5, 2); // Abstand zwischen den Komponenten

        JLabel nameLabel = new JLabel("Nachname");
        JLabel vornameLabel = new JLabel("Vorname");
        gbc.gridx = 0;
        addPanel.add(vornameLabel,gbc);
        gbc.gridx ++;
        addPanel.add(nameLabel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;

        JTextField vorname = new JTextField();
        vorname.setPreferredSize(new Dimension(90,25));
        addPanel.add(vorname, gbc);
        gbc.gridx ++;
        JTextField name = new JTextField();
        name.setPreferredSize(new Dimension(90,25));
        addPanel.add(name, gbc);
        gbc.gridx ++;
        JButton saveButton = new JButton("hinzufügen");
        vorname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !vorname.getText().isEmpty()){
                    name.requestFocus();
                }else if(e.getKeyCode() == KeyEvent.VK_SPACE && !vorname.getText().isEmpty()){
                    vorname.setText(vorname.getText().split(" ")[0]);
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
                if(e.getKeyCode() == KeyEvent.VK_SPACE && !vorname.getText().isEmpty()){
                    if(!vorname.getText().isBlank() && !name.getText().isBlank()){
                        name.setText(name.getText().split(" ")[0]);
                        Game.addPlayer(vorname.getText(),name.getText());
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
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(playerList);
        listScrollPane.setFocusable(false);
        playerList.setFocusable(false);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        playerAddPanel.add(addPanel, BorderLayout.NORTH);
        playerAddPanel.add(listPanel, BorderLayout.CENTER);
        configFrame.add(playerAddPanel, BorderLayout.WEST);

        JTextField changeVornameTextField = new JTextField("");
        changeVornameTextField.setPreferredSize(new Dimension(90,25));
        JTextField changeNachnameTextField = new JTextField("");
        changeNachnameTextField.setPreferredSize(new Dimension(90,25));
        changeNachnameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!changeNachnameTextField.getText().isBlank() && !changeNachnameTextField.getText().isBlank()){
                        System.out.println(playerList.getSelectedValue());
                        System.out.println(playerList.getSelectedValue().split(" ")[0] + "|" + playerList.getSelectedValue().split(" ")[1]);
                        Game.setPlayerName(Objects.requireNonNull(Game.getPlayer(playerList.getSelectedValue().split(" ")[0], playerList.getSelectedValue().split(" ")[1])),changeVornameTextField.getText(), changeNachnameTextField.getText());
                        changeVornameTextField.setText("");
                        changeNachnameTextField.setText("");
                    }
                }
            }
        });
        changeVornameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!changeVornameTextField.getText().isBlank()){
                        changeNachnameTextField.requestFocus();
                    }
                }
            }
        });

        JButton changeNameButton = new JButton("ändern");
        changeNameButton.setFocusable(false);
        changeNameButton.setEnabled(true);
        changeNameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!playerList.isSelectionEmpty() && !changeNachnameTextField.getText().isEmpty()){
                    System.out.println(playerList.getSelectedValue());
                    System.out.println(playerList.getSelectedValue().split(" ")[0] + "|" + playerList.getSelectedValue().split(" ")[1]);
                    Game.setPlayerName(Objects.requireNonNull(Game.getPlayer(playerList.getSelectedValue().split(" ")[0], playerList.getSelectedValue().split(" ")[1])),changeVornameTextField.getText(), changeNachnameTextField.getText());
                    changeVornameTextField.setText("");
                    changeNachnameTextField.setText("");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        changePlayerNamePanel.setVisible(false);
        changePlayerNamePanel.add(new JLabel("neuer Vorname"),gbc);
        gbc.gridx ++;
        changePlayerNamePanel.add(new JLabel("neuer Nachname"),gbc);
        gbc.gridy ++;
        gbc.gridx = 0;
        changePlayerNamePanel.add(changeVornameTextField,gbc);
        gbc.gridx++;
        changePlayerNamePanel.add(changeNachnameTextField,gbc);
        gbc.gridx++;
        changePlayerNamePanel.add(changeNameButton,gbc);
        configFrame.add(changePanel, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        changePanel.add(changePlayerNamePanel);

        JLabel pointsLabel = new JLabel("Punktestand");
        changePlayerPointsPanel.add(pointsLabel, gbc);
        gbc.gridx ++;
        JLabel newPointsLabel = new JLabel("neue Punkte");
        changePlayerPointsPanel.add(newPointsLabel);
        gbc.gridx = 0;
        gbc.gridy ++;
        JTextField pointsTextField = new JTextField();
        pointsTextField.setPreferredSize(new Dimension(90,25));
        changePlayerPointsPanel.add(pointsTextField,gbc);
        gbc.gridx ++;
        JTextField newPointsTextField = new JTextField();
        newPointsTextField.setPreferredSize(new Dimension(90,25));
        changePlayerPointsPanel.add(newPointsTextField,gbc);
        gbc.gridx ++;
        JButton changePointsButton = new JButton("Punkte ändern");
        changePlayerPointsPanel.add(changePointsButton,gbc);
        gbc.gridx = 0;
        changePanel.add(changePlayerPointsPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu playerMenu = new JMenu("Spieler");
        JMenu mainMenu = new JMenu("Allgemein");
        menuBar.add(mainMenu);
        menuBar.add(playerMenu);
        configFrame.setJMenuBar(menuBar);
        playerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changePlayerNamePanel.setVisible(true);
                changePlayerPointsPanel.setVisible(true);
                changeVornameTextField.setText(playerList.getSelectedValue().split(" ")[0]);
                changeNachnameTextField.setText(playerList.getSelectedValue().split(" ")[1]);
                pointsTextField.setText(String.valueOf(Game.getPlayer(playerList.getSelectedValue().split(" ")[0],playerList.getSelectedValue().split(" ")[1]).getPoints()));

            }
        });
        playerMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainConfigPanel.setVisible(false);
                changePanel.setVisible(true);
                playerAddPanel.setVisible(true);
            }
        });
        mainMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playerAddPanel.setVisible(false);
                changePanel.setVisible(false);
                mainConfigPanel.setVisible(true);
            }
        });
        configFrame.add(new JLabel("©Lötgott all rights reserved"),BorderLayout.SOUTH);
        configFrame.setLocationRelativeTo(null);
        configFrame.setVisible(true);
        System.out.println(GREEN + "configFrame generated!" + RESET);
    }

    public static void addPlayerToList(Player nPlayer){
        playerListModel.addElement(nPlayer.getName()[0] + " " + nPlayer.getName()[1]);
    }
    public static void setPlayerName(Player oldPlayer, String newVorname, String newNachname) {
        String oldName = oldPlayer.getName()[0] + " " + oldPlayer.getName()[1];
        int index = playerListModel.indexOf(oldName);
        if (index != -1) {
            playerListModel.setElementAt(newVorname + newNachname, index);
            oldPlayer.setName(newVorname, newNachname);
        }
    }
}