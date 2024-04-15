import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

import com.formdev.flatlaf.*;
import org.jetbrains.annotations.NotNull;


public class Gui {

    public static DefaultListModel<String> configPlayerListModel = new DefaultListModel<>();
    public static JList<String> configPlayerList = new JList<>(configPlayerListModel);
    static JTable leaderboardTable = new JTable(10,3);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[37m";

    public Gui() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame mainFrame = new JFrame("Plan");
        mainFrame.setSize(new Dimension(1920, 1080));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setUndecorated(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        JPanel leaderboardPanel = new JPanel();
        JPanel tischPanel = new JPanel();
        JPanel pointsPanel = new JPanel(new BorderLayout());

        mainPanel.add(tischPanel, BorderLayout.CENTER);
        mainPanel.add(pointsPanel, BorderLayout.EAST);

        leaderboardPanel.setBackground(new Color(255,255,255));
        tischPanel.setBackground(new Color(255,255,255));
        pointsPanel.setBackground(new Color(255,255,255));

        JTable pointsTable = new JTable(8, 3);
        pointsTable.setBackground(new Color(255,255,255));
        pointsTable.setForeground(new Color(15, 117, 0));
        pointsTable.setRowHeight(50);
        pointsTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        pointsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        pointsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        Font tableFont = pointsTable.getFont();
        pointsTable.setFont(tableFont.deriveFont(Font.BOLD, 22));
        pointsTable.setValueAt("Punktewertung:", 0, 0);
        pointsTable.setValueAt("Gewinner",0,1);
        pointsTable.setValueAt("Verlierer",0,2);
        pointsTable.setValueAt("Rufspiel:", 1, 0);
        pointsTable.setValueAt("+ 1", 1, 1);
        pointsTable.setValueAt("- 1", 1, 2);
        pointsTable.setValueAt("Rufspiel Schneider:", 2, 0);
        pointsTable.setValueAt("+ 2", 2, 1);
        pointsTable.setValueAt("- 2", 2, 2);
        pointsTable.setValueAt("Rufspiel Durch:", 3, 0);
        pointsTable.setValueAt("+ 3", 3, 1);
        pointsTable.setValueAt("- 3", 3, 2);
        pointsTable.setValueAt("Solo/Wenz:", 4, 0);
        pointsTable.setValueAt("+ 12", 4, 1);
        pointsTable.setValueAt("- 4", 4, 2);
        pointsTable.setValueAt("Solo/Wenz Schneider:", 5, 0);
        pointsTable.setValueAt("+ 15", 5, 1);
        pointsTable.setValueAt("- 5", 5, 2);
        pointsTable.setValueAt("Solo/Wenz Durch:", 6, 0);
        pointsTable.setValueAt("+ 18", 6, 1);
        pointsTable.setValueAt("- 6", 6, 2);
        pointsTable.setValueAt("Solo-/Wenz-Tout:", 7, 0);
        pointsTable.setValueAt("+ 24", 7, 1);
        pointsTable.setValueAt("- 8", 7, 2);
        pointsTable.setShowGrid(true);
        pointsTable.setShowHorizontalLines(true);
        pointsTable.setShowVerticalLines(false);
        pointsTable.setGridColor(pointsTable.getForeground());
        pointsPanel.add(pointsTable, BorderLayout.CENTER);

        leaderboardTable.setShowGrid(false);
        leaderboardTable.setRowHeight(50);
        leaderboardTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        leaderboardTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        leaderboardTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableFont = leaderboardTable.getFont();
        leaderboardTable.setFont(tableFont.deriveFont(Font.BOLD, 22));
        leaderboardPanel.add(leaderboardTable);
        pointsPanel.add(leaderboardPanel,BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().getLocation());
        mainFrame.setVisible(true);

        //ab hier alles configFrame
        JFrame configFrame = new JFrame("");
        configFrame.setUndecorated(false);
        configFrame.setSize(new Dimension(800, 600));
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ab hier alles Main configPanel
        JPanel mainConfigPanel =new JPanel(new BorderLayout());
        configFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].getDefaultConfiguration().getBounds().getLocation());
        JButton button2 = new JButton();
        button2.setVisible(true);
        mainConfigPanel.add(button2,BorderLayout.NORTH);

        mainConfigPanel.setVisible(true);


        //ab hier alles Player Panel
        JPanel playerAddPanel = new JPanel(new BorderLayout());
        playerAddPanel.setVisible(false);
        JPanel addPanel = new JPanel(new GridBagLayout());
        JPanel listPanel = new JPanel(new BorderLayout()); // Use BorderLayout
        JPanel changePanel = new JPanel(new GridBagLayout());
        JPanel changePlayerNamePanel = new JPanel(new GridBagLayout());
        JPanel changePlayerPointsPanel = new JPanel(new GridBagLayout());
        JPanel changePlayerTischPanel = new JPanel(new GridBagLayout());
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

        configPlayerList.setSelectionForeground(Color.WHITE);
        configPlayerList.setSelectionBackground(new Color(116, 116, 121));
        configPlayerList.setBackground(configFrame.getBackground());
        configPlayerList.setForeground(Color.WHITE);
        configPlayerList.setFont(configFrame.getFont());
        configPlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(configPlayerList);
        listScrollPane.setFocusable(false);
        configPlayerList.setFocusable(false);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        playerAddPanel.add(addPanel, BorderLayout.NORTH);
        playerAddPanel.add(listPanel, BorderLayout.CENTER);
        configFrame.add(playerAddPanel, BorderLayout.WEST);

        // ab hier changePanel
        gbc.anchor = GridBagConstraints.WEST; // Ausrichtung der Komponenten am linken Rand
        JTextField changeVornameTextField = new JTextField("");
        changeVornameTextField.setPreferredSize(new Dimension(90,25));
        JTextField changeNachnameTextField = new JTextField("");
        changeNachnameTextField.setPreferredSize(new Dimension(90,25));
        changeNachnameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!changeNachnameTextField.getText().isBlank() && !changeNachnameTextField.getText().isBlank()){
                        Game.setPlayerName(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])),changeVornameTextField.getText(), changeNachnameTextField.getText());
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
                if(!configPlayerList.isSelectionEmpty() && !changeNachnameTextField.getText().isEmpty()){
                    Game.setPlayerName(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])),changeVornameTextField.getText(), changeNachnameTextField.getText());
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
        changePanel.add(changePlayerNamePanel,gbc);
        JLabel pointsLabel = new JLabel("Punktestand");
        changePlayerPointsPanel.add(pointsLabel, gbc);
        gbc.gridx ++;
        JLabel newPointsLabel = new JLabel("neue Punkte");
        changePlayerPointsPanel.add(newPointsLabel);
        gbc.gridx = 0;
        gbc.gridy ++;
        JTextField pointsTextField = new JTextField();
        pointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        pointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!pointsTextField.getText().isBlank()){
                        JDialog pointsChangeConfirmationFrame = new JDialog();
                        JMenuBar buttonBar = new JMenuBar();
                        JButton confirmButton = new JButton("ändern");
                        JButton denyButton = new JButton("abbrechen");
                        buttonBar.add(confirmButton);
                        buttonBar.add(denyButton);
                        buttonBar.setBorderPainted(false);
                        buttonBar.setBackground(pointsChangeConfirmationFrame.getBackground());
                        buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                        pointsChangeConfirmationFrame.add(buttonBar, BorderLayout.SOUTH);
                        pointsChangeConfirmationFrame.setSize(new Dimension(400, 100));
                        JPanel textPanel = new JPanel(new GridBagLayout());
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.anchor = GridBagConstraints.CENTER;
                        textPanel.add(new JLabel("Achtung: Soll wirklich der Punktestand geändert werden?"), gbc);
                        pointsChangeConfirmationFrame.add(textPanel, BorderLayout.CENTER);
                        confirmButton.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                                if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                    denyButton.requestFocus();
                                }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    Game.setPlayerPoints(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])),Integer.parseInt(pointsTextField.getText()));
                                    pointsChangeConfirmationFrame.dispose();
                                }
                            }
                        });
                        confirmButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                Game.setPlayerPoints(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])),Integer.parseInt(pointsTextField.getText()));
                                pointsChangeConfirmationFrame.dispose();                            }
                        });
                        denyButton.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                                if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                    confirmButton.requestFocus();
                                }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    pointsChangeConfirmationFrame.dispose();
                                }
                            }
                        });
                        denyButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                pointsChangeConfirmationFrame.dispose();
                            }
                        });
                        confirmButton.requestFocus();
                        pointsChangeConfirmationFrame.setVisible(true);

                    }
                }
            }
        });
        pointsTextField.setPreferredSize(new Dimension(90,25));
        changePlayerPointsPanel.add(pointsTextField,gbc);
        gbc.gridx ++;
        JTextField newPointsTextField = new JTextField();
        newPointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!pointsTextField.getText().isBlank()){
                        Game.addPlayerPoints(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])),Integer.parseInt(newPointsTextField.getText()));
                        pointsTextField.setText(String.valueOf(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])).getPoints()));
                        newPointsTextField.setText("");
                        changePanel.requestFocusInWindow();

                    }
                }
            }
        });
        newPointsTextField.setPreferredSize(new Dimension(90,25));
        newPointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        changePlayerPointsPanel.add(newPointsTextField,gbc);
        gbc.gridx ++;
        JButton changePointsButton = new JButton("Punkte ändern");
        changePointsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.addPlayerPoints(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])),Integer.parseInt(newPointsTextField.getText()));
                pointsTextField.setText(String.valueOf(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])).getPoints()));
                newPointsTextField.setText("");
                changePanel.requestFocusInWindow();

            }
        });
        changePlayerPointsPanel.add(changePointsButton,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        changePanel.add(changePlayerPointsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel TischLabel = new JLabel("Tisch");
        TischLabel.setPreferredSize(new Dimension(90,25));
        changePlayerTischPanel.add(TischLabel,gbc);
        gbc.gridx ++;
        JLabel newTischLabel = new JLabel("neuer Tisch");
        changePlayerTischPanel.add(newTischLabel,gbc);
        gbc.gridx = 0;
        gbc.gridy ++;
        JLabel TischLabelNow = new JLabel();
        changePlayerTischPanel.add(TischLabelNow,gbc);
        gbc.gridx ++;
        JTextField newTischTextField = new JTextField();
        newTischTextField.setPreferredSize(new Dimension(90,25));
        changePlayerTischPanel.add(newTischTextField,gbc);
        gbc.gridx ++;
        JButton changePlayerTischButton = new JButton("ändern");
        changePlayerTischPanel.add(changePlayerTischButton, gbc);
        newTischTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        changePlayerTischButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!newTischTextField.getText().isBlank() && Game.tischList.size() > Integer.parseInt(newTischTextField.getText())){
                    JDialog tischChangeFrame = new JDialog();
                    JMenuBar buttonBar = new JMenuBar();
                    JButton confirmButton = new JButton("ändern");
                    JButton denyButton = new JButton("abbrechen");
                    buttonBar.add(confirmButton);
                    buttonBar.add(denyButton);
                    buttonBar.setBorderPainted(false);
                    buttonBar.setBackground(tischChangeFrame.getBackground());
                    buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    tischChangeFrame.add(buttonBar, BorderLayout.SOUTH);
                    tischChangeFrame.setSize(new Dimension(400, 100));
                    JPanel textPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    textPanel.add(new JLabel("Bitte Wechselspieler auswählen"),gbc);
                    int number = Integer.parseInt(newTischTextField.getText());
                    gbc.gridy ++;
                    JMenu playerMenu = new JMenu();
                    ArrayList<Player> chnageTischPlayerList = Game.tischList.get(number).getPlayerList();
                    Player changePlayer1 = Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0],configPlayerList.getSelectedValue().split(" ")[1]);
                    final Player[] changePlayer2 = new Player[1];
                    for (Player player : chnageTischPlayerList) {
                        JMenuItem playerItem = new JMenuItem(player.getName()[0] + " " + player.getName()[1]);
                        playerItem.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                changePlayer2[0] = player;
                            }
                        });
                        playerMenu.add(playerItem);
                    }

                    tischChangeFrame.add(textPanel, BorderLayout.CENTER);
                    confirmButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                denyButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                //TODO Methode zu tischtausch changeTisch(changePlayer1,changePlayer2);
                            }
                        }
                    });
                    confirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            //TODO Methode zum Tischtausch
                        }
                    });
                    denyButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                confirmButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                tischChangeFrame.dispose();
                            }
                        }
                    });
                    denyButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tischChangeFrame.dispose();
                        }
                    });
                    confirmButton.requestFocus();
                    tischChangeFrame.setVisible(true);
                }else if(Integer.parseInt(newTischTextField.getText()) >= Game.tischList.size()){
                    System.out.println(RED + "tisch number is too big!" + RESET);
                }
            }
        });
        newTischTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!newTischTextField.getText().isBlank() && Game.tischList.size() > Integer.parseInt(newTischTextField.getText()) && e.getKeyCode() == KeyEvent.VK_ENTER){
                    JDialog tischChangeFrame = new JDialog();
                    JMenuBar buttonBar = new JMenuBar();
                    JButton confirmButton = new JButton("ändern");
                    JButton denyButton = new JButton("abbrechen");
                    buttonBar.add(confirmButton);
                    buttonBar.add(denyButton);
                    buttonBar.setBorderPainted(false);
                    buttonBar.setBackground(tischChangeFrame.getBackground());
                    buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    tischChangeFrame.add(buttonBar, BorderLayout.SOUTH);
                    tischChangeFrame.setSize(new Dimension(400, 100));
                    JPanel textPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    textPanel.add(new JLabel("Bitte Wechselspieler auswählen"),gbc);
                    int number = Integer.parseInt(newTischTextField.getText());
                    gbc.gridy ++;
                    JMenu playerMenu = new JMenu();
                    ArrayList<Player> chnageTischPlayerList = Game.tischList.get(number).getPlayerList();
                    Player changePlayer1 = Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0],configPlayerList.getSelectedValue().split(" ")[1]);
                    final Player[] changePlayer2 = new Player[1];
                    for (Player player : chnageTischPlayerList) {
                        JMenuItem playerItem = new JMenuItem(player.getName()[0] + " " + player.getName()[1]);
                        playerItem.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                changePlayer2[0] = player;
                            }
                        });
                        playerMenu.add(playerItem);
                    }

                    tischChangeFrame.add(textPanel, BorderLayout.CENTER);
                    confirmButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                denyButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                //TODO Methode zu tischtausch changeTisch(changePlayer1,changePlayer2);
                            }
                        }
                    });
                    confirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            //TODO Methode zum Tischtausch
                        }
                    });
                    denyButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                confirmButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                tischChangeFrame.dispose();
                            }
                        }
                    });
                    denyButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tischChangeFrame.dispose();
                        }
                    });
                    confirmButton.requestFocus();
                    tischChangeFrame.setVisible(true);
                }else if(Integer.parseInt(newTischTextField.getText()) >= Game.tischList.size()){
                    System.out.println(RED + "tisch number is too big!" + RESET);
                }
            }
        });
        changePlayerTischPanel.setVisible(false);
        gbc.gridy = 2;
        gbc.gridx = 0;
        changePanel.add(changePlayerTischPanel, gbc);

        JMenuBar menuBar = new JMenuBar();
        JMenu playerMenu = new JMenu("Spieler");
        JMenu mainMenu = new JMenu("Allgemein");
        JMenu updateMenu = new JMenu("Update");
        JMenuItem updateLeaderboard = new JMenuItem("update Leaderboard");
        JMenuItem updateTische = new JMenuItem("update Tische");
        updateMenu.add(updateLeaderboard);
        updateLeaderboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Game.updateLeaderboard();
            }
        });
        updateMenu.add(updateTische);
        menuBar.add(mainMenu);
        menuBar.add(playerMenu);
        menuBar.add(updateMenu);
        configFrame.setJMenuBar(menuBar);
        configPlayerList.addListSelectionListener(e -> {
            changePlayerNamePanel.setVisible(true);
            changePlayerPointsPanel.setVisible(true);
            changePlayerTischPanel.setVisible(true);
            changeVornameTextField.setText(configPlayerList.getSelectedValue().split(" ")[0]);
            changeNachnameTextField.setText(configPlayerList.getSelectedValue().split(" ")[1]);
            pointsTextField.setText(String.valueOf(Objects.requireNonNull(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1])).getPoints()));
            if(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1]).getTisch() != null){
                TischLabelNow.setText(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1]).getTisch().getName());
                newTischTextField.setText(Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0], configPlayerList.getSelectedValue().split(" ")[1]).getTisch().getName());
            }else{
                TischLabelNow.setText("N/A");
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
        configFrame.add(new JLabel("©Lötgott && sesamoel all rights reserved"),BorderLayout.SOUTH);
        configFrame.setLocationRelativeTo(null);
        configFrame.setVisible(true);
        System.out.println(GREEN + "configFrame generated!" + RESET);
    }

    public static void addPlayerToList(@NotNull Player nPlayer){
        configPlayerListModel.addElement(nPlayer.getName()[0] + " " + nPlayer.getName()[1]);
    }
    public static void setPlayerName(@NotNull Player oldPlayer, String newVorname, String newNachname) {
        String oldName = oldPlayer.getName()[0] + " " + oldPlayer.getName()[1];
        int index = configPlayerListModel.indexOf(oldName);
        if (index != -1) {
            configPlayerListModel.setElementAt(newVorname + " " + newNachname, index);
            oldPlayer.setName(newVorname, newNachname);
            System.out.println(GREEN + "Der Name des Spielers " + oldName + " wurde zu " + newVorname + " " + newNachname +" geändert");
        }
    }
    public static void updateLeaderboard(ArrayList<Player> playerlist){
        for(int i = 0; i < 10; i++){
            if(playerlist.size() > i){
                leaderboardTable.setValueAt(i + 1,i,0);
                leaderboardTable.setValueAt(playerlist.get(i).getName()[0] + " " + playerlist.get(i).getName()[1].charAt(0) + ".",i,1);
                leaderboardTable.setValueAt(playerlist.get(i).getPoints(),i,2);
            }
        }
        System.out.println(YELLOW + "updated the leaderboard" + RESET);
    }
}