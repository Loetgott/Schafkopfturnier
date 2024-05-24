import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import com.formdev.flatlaf.*;
import org.jetbrains.annotations.NotNull;


public class Gui {

    public static DefaultListModel<String> configPlayerListModel = new DefaultListModel<>();
    public static JList<String> configPlayerList = new JList<>(configPlayerListModel);
    public static DefaultListModel<String> configTischlistModel = new DefaultListModel<>();
    public static JList<String> configTischList = new JList<>(configTischlistModel);
    public static DefaultListModel<String> configTischPlayerListModel = new DefaultListModel<>();
    public static JList<String> configTischPlayerList = new JList<>(configTischPlayerListModel);
    public static JTable clientTable = new JTable(10,3);
    static JTable leaderboardTable = new JTable(10,3);
    public static ArrayList<JTisch> tischList = new ArrayList<>();
    public static JLabel leaderboardLabel = new JLabel("<html><u>Leaderboard:</u></html>");
    public static String backupPath = "C:/Users/nnaml/OneDrive/Schule/P-Seminar-Schafkopfen/";
    public static Player changePlayer;


    public Gui() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
            UIManager.put( "Component.focusWidth", 1 );
            UIManager.put( "ScrollBar.thumbArc", 999 );
            UIManager.put( "ScrollBar.thumbInsets", new Insets( 2, 2, 2, 2 ) );
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        JFrame mainFrame = new JFrame("Plan");
        mainFrame.setSize(new Dimension(1920, 1080));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setUndecorated(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        JPanel leaderboardPanel = new JPanel(new BorderLayout());
        JPanel tischPanel = new JPanel(new GridBagLayout());
        JPanel pointsPanel = new JPanel(new BorderLayout());

        mainPanel.add(tischPanel, BorderLayout.CENTER);
        mainPanel.add(pointsPanel, BorderLayout.EAST);

        leaderboardPanel.setBackground(new Color(255,255,255));
        tischPanel.setBackground(new Color(255,255,255));

        gbc.insets = new Insets(5, 5, 5, 5);
        int tischNumber = 1;
        for(int i = 0; i < 5; i ++){
            for(int ii = 0; ii < 4; ii ++){
                JTisch tisch = new JTisch();
                tisch.setName(String.valueOf(tischNumber));
                tischNumber ++;
                gbc.gridx = ii;
                gbc.gridy = i;
                tischList.add(tisch);
                tischPanel.add(tisch, gbc);
            }
        }
        gbc.insets = new Insets(0, 0, 0, 0);

        pointsPanel.setBackground(new Color(255,255,255));

        JTable pointsTable = new JTable(9, 3);
        pointsTable.setBackground(new Color(255,255,255));
        pointsTable.setForeground(new Color(15, 117, 0));
        pointsTable.setRowHeight(50);
        pointsTable.getColumnModel().getColumn(0).setPreferredWidth(325);
        pointsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        pointsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        Font tableFont = pointsTable.getFont();
        pointsTable.setFont(tableFont.deriveFont(Font.BOLD, 22));
        pointsTable.setValueAt("Punktewertung:", 0, 0);
        pointsTable.setValueAt("Spieler",0,1);
        pointsTable.setValueAt("Spieler",0,2);
        pointsTable.setValueAt("Rufspiel:", 1, 0);
        pointsTable.setValueAt("1", 1, 1);
        pointsTable.setValueAt("1", 1, 2);
        pointsTable.setValueAt("Rufspiel Schneider:", 2, 0);
        pointsTable.setValueAt("2", 2, 1);
        pointsTable.setValueAt("2", 2, 2);
        pointsTable.setValueAt("Rufspiel Schneider Schwarz:", 3, 0);
        pointsTable.setValueAt("3", 3, 1);
        pointsTable.setValueAt("3", 3, 2);
        pointsTable.setValueAt("Solo/Wenz:", 4, 0);
        pointsTable.setValueAt("6", 4, 1);
        pointsTable.setValueAt("2", 4, 2);
        pointsTable.setValueAt("Solo/Wenz Schneider:", 5, 0);
        pointsTable.setValueAt("9", 5, 1);
        pointsTable.setValueAt("3", 5, 2);
        pointsTable.setValueAt("Solo/Wenz Schneider Schwarz:", 6, 0);
        pointsTable.setValueAt("12", 6, 1);
        pointsTable.setValueAt("4", 6, 2);
        pointsTable.setValueAt("Solo-/Wenz-Tout:", 7, 0);
        pointsTable.setValueAt("18", 7, 1);
        pointsTable.setValueAt("6", 7, 2);
        pointsTable.setValueAt("Sie", 8,0);
        pointsTable.setValueAt("24",8,1);
        pointsTable.setValueAt("8",8,2);
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
        leaderboardLabel.setForeground(pointsTable.getForeground());
        leaderboardLabel.setFont(leaderboardLabel.getFont().deriveFont(Font.BOLD,30));
        leaderboardLabel.setPreferredSize(new Dimension(400,100));
        leaderboardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leaderboardLabel.setVisible(false);
        leaderboardTable.setFont(tableFont.deriveFont(Font.BOLD, 22));
        leaderboardTable.setForeground(pointsTable.getForeground());
        leaderboardPanel.add(leaderboardLabel, BorderLayout.NORTH);
        leaderboardPanel.add(leaderboardTable,BorderLayout.CENTER);
        pointsPanel.add(leaderboardPanel,BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().getLocation());
        mainFrame.setVisible(true);
        System.out.println(Game.GREEN + "mainFrame generated" + Game.RESET);
        ImageIcon frameIcon = new ImageIcon("C:\\Users\\nnaml\\Downloads\\schafkopf-clipart-17.jpg");
        mainFrame.setIconImage(frameIcon.getImage());

        //ab hier alles configFrame
        JFrame configFrame = new JFrame("");
        configFrame.setIconImage(frameIcon.getImage());
        configFrame.setUndecorated(false);
        configFrame.setSize(new Dimension(800, 600));
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().getLocation());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainTab = new JPanel();
        JPanel playerTab = new JPanel(new BorderLayout());
        JPanel tischTab = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel serverPanel = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Allgemein", mainTab);
        tabbedPane.addTab("Spieler", playerTab);
        tabbedPane.addTab("Tische", tischTab);
        tabbedPane.addTab("Server", serverPanel);
        configFrame.add(tabbedPane);

        //ab hier alles Player Panel
        JPanel playerAddPanel = new JPanel(new BorderLayout());
        playerAddPanel.setVisible(false);
        JPanel addPanel = new JPanel(new GridBagLayout());
        JPanel listPanel = new JPanel(new FlowLayout()); // Use BorderLayout
        JPanel changePanel = new JPanel(new GridBagLayout());
        JPanel changePlayerNamePanel = new JPanel(new GridBagLayout());
        JPanel changePlayerPointsPanel = new JPanel(new GridBagLayout());
        JPanel changePlayerTischPanel = new JPanel(new GridBagLayout());
        JPanel changePlayerNextRoundPanel = new JPanel(new GridBagLayout());
        changePlayerPointsPanel.setVisible(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 2, 5, 2);

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
                }else if(e.getKeyCode() == KeyEvent.VK_SPACE && !vorname.getText().isEmpty() && !(Objects.equals(vorname.getText(), " "))){
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
        configFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                listScrollPane.setPreferredSize(new Dimension(300,(configFrame.getHeight() - 160)));
                listScrollPane.repaint();
                configPlayerList.repaint();
                listScrollPane.getHorizontalScrollBar().revalidate();
                listScrollPane.revalidate();
                configPlayerList.revalidate();
            }
        });
        listScrollPane.setVisible(true);
        configPlayerList.setFocusable(false);
        listPanel.add(listScrollPane);
        playerAddPanel.add(addPanel, BorderLayout.NORTH);

        // ab hier changePanel
        gbc.anchor = GridBagConstraints.WEST;
        JTextField changeVornameTextField = new JTextField("");
        changeVornameTextField.setPreferredSize(new Dimension(90,25));
        JTextField changeNachnameTextField = new JTextField("");
        changeNachnameTextField.setPreferredSize(new Dimension(90,25));
        changeNachnameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!changeNachnameTextField.getText().isBlank() && !changeNachnameTextField.getText().isBlank()){
                        Game.setPlayerName(Objects.requireNonNull(changePlayer),changeVornameTextField.getText(), changeNachnameTextField.getText());
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
                    Game.setPlayerName(Objects.requireNonNull(changePlayer),changeVornameTextField.getText(), changeNachnameTextField.getText());
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        changePlayerNamePanel.setVisible(true);
        changePlayerNamePanel.add(new JLabel("Vorname"),gbc);
        gbc.gridx ++;
        changePlayerNamePanel.add(new JLabel("Nachname"),gbc);
        gbc.gridy ++;
        gbc.gridx = 0;
        changePlayerNamePanel.add(changeVornameTextField,gbc);
        gbc.gridx++;
        changePlayerNamePanel.add(changeNachnameTextField,gbc);
        gbc.gridx++;
        changePlayerNamePanel.add(changeNameButton,gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        changePanel.add(changePlayerNamePanel,gbc);
        changePlayerPointsPanel.add(new JLabel("Punktestand"), gbc);
        gbc.gridx ++;
        changePlayerPointsPanel.add(new JLabel("Rundenpunkte"), gbc);
        gbc.gridx ++;
        changePlayerPointsPanel.add(new JLabel("neue Punkte"),gbc);
        gbc.gridx = 0;
        gbc.gridy ++;
        JTextField pointsTextField = new JTextField();
        pointsTextField.setPreferredSize(new Dimension(90,25));
        changePlayerPointsPanel.add(pointsTextField,gbc);
        gbc.gridx ++;
        JTextField roundPointsTextField = new JTextField();
        roundPointsTextField.setPreferredSize(new Dimension(90,25));
        changePlayerPointsPanel.add(roundPointsTextField,gbc);
        gbc.gridx ++;
        JTextField newPointsTextField = new JTextField();
        newPointsTextField.setPreferredSize(new Dimension(90,25));
        changePlayerPointsPanel.add(newPointsTextField,gbc);

        roundPointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
                    if(!pointsTextField.getText().isBlank()){
                        Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).roundPoints = Integer.parseInt(roundPointsTextField.getText());
                        pointsTextField.setText(String.valueOf(Objects.requireNonNull(changePlayer).getPoints()));
                        changePanel.requestFocusInWindow();
                    }
                }
            }
        });
        pointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == '-') || (c == '+'))) {
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
                                    Game.setPlayerPoints(Objects.requireNonNull(changePlayer),Integer.parseInt(pointsTextField.getText()));
                                    pointsChangeConfirmationFrame.dispose();
                                }
                            }
                        });
                        confirmButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                Game.setPlayerPoints(Objects.requireNonNull(changePlayer),Integer.parseInt(pointsTextField.getText()));
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
        newPointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == '-') || (c == '+'))) {
                    e.consume();
                }
            }
        });
        newPointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Game.addPlayerPoints(changePlayer,Integer.parseInt(newPointsTextField.getText()));
                    newPointsTextField.setText("");
                    roundPointsTextField.setText(String.valueOf(Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).roundPoints));
                    changePanel.requestFocusInWindow();
                }
            }
        });
        roundPointsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((Character.isLetter(c))){
                    e.consume();
                }
            }
        });

        gbc.gridx ++;
        JButton changePointsButton = new JButton("Punkte hinzufügen");
        changePointsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Game.addPlayerPoints(changePlayer,Integer.parseInt(newPointsTextField.getText()));
                newPointsTextField.setText("");
                roundPointsTextField.setText(String.valueOf(Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).roundPoints));
                changePanel.requestFocusInWindow();
                //for(Player player : changePlayer.getTisch().getPlayerList()){
                //    if(changePlayer.getRoundPoints() == player.getRoundPoints() && changePlayer != player){
                //        configTischPlayerList.add((new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\list.png"),0);
                //    }
                //}
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
                if ((Character.isLetter(c))) {
                    e.consume();
                }
            }
        });
        changePlayerTischButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {    if((!newTischTextField.getText().isBlank() && (Game.tischList.size() > Integer.parseInt(newTischTextField.getText()) - 1))){
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
                    tischChangeFrame.setSize(new Dimension(250, 150));
                    tischChangeFrame.setLocation(configFrame.getLocationOnScreen());
                    JPanel textPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    textPanel.add(new JLabel("Bitte Wechselspieler auswählen"),gbc);
                    int number = Integer.parseInt(newTischTextField.getText()) - 1;
                    JMenuBar playerBar = new JMenuBar();
                    JMenu playerMenu = new JMenu("Tauschspieler");
                    ArrayList<Player> changePlayerList = Game.tischList.get(number).getPlayerList();
                    Player changePlayer1 = changePlayer;
                    final Player[] changePlayer2 = new Player[1];
                    for (Player player : changePlayerList) {
                        JMenuItem playerItem = new JMenuItem(player.getName()[0] + " " + player.getName()[1]);
                        playerItem.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e){
                                changePlayer2[0] = player;
                                playerMenu.setText(player.getVorname() + " " + player.getNachname());
                            }
                        });
                        playerMenu.add(playerItem);
                    }
                    playerMenu.setVisible(true);
                    gbc.gridy = 1;
                    gbc.gridx = 0;
                    playerBar.add(playerMenu);
                    textPanel.add(playerBar, gbc);
                    tischChangeFrame.add(textPanel, BorderLayout.CENTER);
                    confirmButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                denyButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                Game.spielertausch(changePlayer1,changePlayer2[0]);
                                tischChangeFrame.dispose();
                                TischLabelNow.setText(changePlayer.getTisch().getName());

                            }
                        }
                    });
                    confirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Game.spielertausch(changePlayer1,changePlayer2[0]);
                            tischChangeFrame.dispose();
                            TischLabelNow.setText(changePlayer.getTisch().getName());
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
            }else if(! newTischTextField.getText().isEmpty()){
                if(Integer.parseInt(newTischTextField.getText()) - 1 >= Game.tischList.size()) {
                    System.out.println(Game.RED + "tisch number is too big!" + Game.RESET);
                }
            }
            }
        });
        newTischTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) )) {
                    e.consume();
                }
            }
        });
        newTischTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((!newTischTextField.getText().isBlank()) && (Game.tischList.size() > Integer.parseInt(newTischTextField.getText()) - 1 ) && e.getKeyCode() == KeyEvent.VK_ENTER){
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
                    tischChangeFrame.setSize(new Dimension(250, 150));
                    tischChangeFrame.setLocation(configFrame.getLocationOnScreen());
                    JPanel textPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    textPanel.add(new JLabel("Bitte Wechselspieler auswählen"),gbc);
                    int number = Integer.parseInt(newTischTextField.getText()) - 1;
                    JMenuBar playerBar = new JMenuBar();
                    JMenu playerMenu = new JMenu("Tauschspieler");
                    ArrayList<Player> changePlayerList = Game.tischList.get(number).getPlayerList();
                    Player changePlayer1 = changePlayer;
                    final Player[] changePlayer2 = new Player[1];
                    for (Player player : changePlayerList) {
                        JMenuItem playerItem = new JMenuItem(player.getName()[0] + " " + player.getName()[1]);
                        playerItem.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e){
                                changePlayer2[0] = player;
                                playerMenu.setText(player.getVorname() + " " + player.getNachname());
                            }
                        });
                        playerMenu.add(playerItem);
                    }
                    playerMenu.setVisible(true);
                    gbc.gridy = 1;
                    gbc.gridx = 0;
                    playerBar.add(playerMenu);
                    textPanel.add(playerBar, gbc);
                    tischChangeFrame.add(textPanel, BorderLayout.CENTER);
                    confirmButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                denyButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                Game.spielertausch(changePlayer1,changePlayer2[0]);
                                tischChangeFrame.dispose();
                                TischLabelNow.setText(changePlayer.getTisch().getName());
                            }
                        }
                    });
                    confirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Game.spielertausch(changePlayer1,changePlayer2[0]);
                            tischChangeFrame.dispose();
                            TischLabelNow.setText(changePlayer.getTisch().getName());
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
                }else if(!newTischTextField.getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(Integer.parseInt(newTischTextField.getText()) - 1 >= Game.tischList.size()) {
                        System.out.println(Game.RED + "tisch number is too big!" + Game.RESET);
                    }
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0 ;
        changePlayerNextRoundPanel.add(new JLabel("nächster Tisch"),gbc);
        gbc.gridy ++;
        JTextField nextRoundTischTextField = new JTextField();
        nextRoundTischTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_DELETE) )) {
                    e.consume();
                }
            }
        });
        nextRoundTischTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(!nextRoundTischTextField.getText().isEmpty()){
                        if(Integer.parseInt(nextRoundTischTextField.getText()) < changePlayer.getTisch().number){
                            Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).steigtAuf = false;
                            Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).nextTischSet = true;
                        }else if(Integer.parseInt(nextRoundTischTextField.getText()) > changePlayer.getTisch().number){
                            Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).steigtAuf = true;
                            Game.getPlayer(changePlayer.getVorname(), changePlayer.getNachname()).nextTischSet = true;
                        }else{
                            System.out.println(Game.RED + "Achtung, der Tisch muss sich in der nächsten Runde ändern! " + Game.RESET);
                        }
                    }
                    if(changePlayer.steigtAuf && changePlayer.nextTischSet){
                        if(changePlayer.tisch.number != Game.tischList.size()){
                            nextRoundTischTextField.setText(String.valueOf(changePlayer.getTisch().getNumber() + 1));
                        }else{
                            nextRoundTischTextField.setText("1");
                        }
                    }else if(changePlayer.nextTischSet){
                        if(changePlayer.tisch.number != 1){
                            nextRoundTischTextField.setText(String.valueOf(changePlayer.getTisch().getNumber() - 1));
                        }else{
                            nextRoundTischTextField.setText(String.valueOf(Game.tischList.size()));
                        }
                    }else{
                        nextRoundTischTextField.setText("N/A");
                    }
                    configFrame.requestFocusInWindow();
                }
            }
        });
        changePlayerNextRoundPanel.add(nextRoundTischTextField,gbc);
        JButton updateNextRoundTischButton = new JButton("nächsten Tisch berechnen");
        gbc.gridx ++;
        updateNextRoundTischButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Game.getTisch(configTischList.getSelectedValue().split(" ")[1]).sortPlayers();
            }
        });
        changePlayerNextRoundPanel.add(updateNextRoundTischButton,gbc);
        gbc.gridx ++;
        JButton resetNextTischSelectionButton = new JButton("zurücksetzen");
        changePlayerNextRoundPanel.add(resetNextTischSelectionButton,gbc);
        changePlayerNextRoundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                changePlayer.nextTischSet = false;
            }
        });
        changePlayerNextRoundPanel.setVisible(true);
        gbc.gridx = 0;
        gbc.gridy = 3;
        changePanel.add(changePlayerNextRoundPanel,gbc);

        configPlayerList.addListSelectionListener(e -> {
            if(!configPlayerList.isSelectionEmpty()){
                changePlayer = Game.getPlayer(configPlayerList.getSelectedValue().split(" ")[0],configPlayerList.getSelectedValue().split(" ")[1]);
                changePlayerNamePanel.setVisible(true);
                changePlayerPointsPanel.setVisible(true);
                changePlayerTischPanel.setVisible(true);
                changeVornameTextField.setText(changePlayer.getVorname());
                changeNachnameTextField.setText(changePlayer.getNachname());
                pointsTextField.setText(String.valueOf(Objects.requireNonNull(changePlayer).getPoints()));
                if(changePlayer.steigtAuf && changePlayer.nextTischSet){
                    if(changePlayer.tisch.number != Game.tischList.size()){
                        nextRoundTischTextField.setText(String.valueOf(changePlayer.getTisch().getNumber() + 1));
                    }else{
                        nextRoundTischTextField.setText("1");
                    }
                }else if(changePlayer.nextTischSet){
                    if(changePlayer.tisch.number != 1){
                        nextRoundTischTextField.setText(String.valueOf(changePlayer.getTisch().getNumber() - 1));
                    }else{
                        nextRoundTischTextField.setText(String.valueOf(Game.tischList.size()));
                    }
                }else{
                    nextRoundTischTextField.setText("N/A");
                }
                roundPointsTextField.setText(String.valueOf((Objects.requireNonNull(changePlayer).getRoundPoints())));
                if(changePlayer.getTisch() != null){
                    TischLabelNow.setText(changePlayer.getTisch().getName());
                    newTischTextField.setText(changePlayer.getTisch().getName());
                }else{
                    TischLabelNow.setText("N/A");
                }
            }
        });
        changePlayerTischPanel.setVisible(true);
        gbc.gridy = 2;
        gbc.gridx = 0;
        changePanel.add(changePlayerTischPanel, gbc);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        ImageIcon fileIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\folder.png");
        fileIcon.setImage(fileIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        fileMenu.setIcon(fileIcon);
        JMenuItem importMenuItem = new JMenuItem("import");
        ImageIcon importIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\import.png");
        importIcon.setImage(importIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        importMenuItem.setIcon(importIcon);
        importMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileFilter() {
                    public boolean accept(File file) {
                        return file.isDirectory() || file.getName().toLowerCase().endsWith(".xml");
                    }

                    @Override
                    public String getDescription() {
                        return "XML files (*.xml)";
                    }
                });
                fileChooser.setCurrentDirectory(new File("C:/Users/nnaml/OneDrive/Schule/P-Seminar-Schafkopfen/"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    Game.xmlMaker.importBackup(selectedFile.getAbsolutePath());
                }
            }
        });
        JMenuItem exportMenuItem = new JMenuItem("export");
        ImageIcon exportIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\export.png");
        exportIcon.setImage(exportIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        exportMenuItem.setIcon(exportIcon);
        exportMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setCurrentDirectory(new File("C:/Users/nnaml/OneDrive/Schule/P-Seminar-Schafkopfen/"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    backupPath = selectedFile.getAbsolutePath();
                    Game.saveBackup(backupPath);
                }
            }
        });
        fileMenu.add(importMenuItem);
        fileMenu.add(exportMenuItem);
        menuBar.add(fileMenu);
        JMenu updateMenu = new JMenu("Update");
        ImageIcon updateIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\updating.png");
        updateIcon.setImage(updateIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        updateMenu.setIcon(updateIcon);
        JMenuItem playerZuordnen = new JMenuItem("Spieler verteilen");
        ImageIcon distributeIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\population.png");
        distributeIcon.setImage(distributeIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        playerZuordnen.setIcon(distributeIcon);
        JMenuItem updateLeaderboard = new JMenuItem("update Leaderboard");
        ImageIcon listIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\list.png");
        listIcon.setImage(listIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        updateLeaderboard.setIcon(listIcon);
        JMenuItem updateTische = new JMenuItem("update Tische");
        ImageIcon tischIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\table.png");
        tischIcon.setImage(tischIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        updateTische.setIcon(tischIcon);
        JMenuItem nextRoundItem = new JMenuItem("nächste Runde");
        ImageIcon nextRoundIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\next.png");
        nextRoundIcon.setImage(nextRoundIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        nextRoundItem.setIcon(nextRoundIcon);
        updateMenu.add(playerZuordnen);
        updateMenu.add(updateLeaderboard);
        playerZuordnen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Game.spielerZuordnen();
            }
        });
        updateLeaderboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Game.updateLeaderboard();
            }
        });
        updateTische.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Game.updateTisch();
            }
        });
        updateMenu.add(updateTische);
        nextRoundItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(Game.nextRoundChangedPlayers().isEmpty()){
                    Game.nextRound();
                }else{
                    JDialog tischChangeFrame = new JDialog();
                    JMenuBar buttonBar = new JMenuBar();
                    JButton confirmButton = new JButton("weiter");
                    JButton denyButton = new JButton("abbrechen");
                    buttonBar.add(confirmButton);
                    buttonBar.add(denyButton);
                    buttonBar.setBorderPainted(false);
                    buttonBar.setBackground(tischChangeFrame.getBackground());
                    buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    tischChangeFrame.add(buttonBar, BorderLayout.SOUTH);
                    tischChangeFrame.setSize(new Dimension(250, 150));
                    tischChangeFrame.setLocation(configFrame.getLocationOnScreen());
                    JPanel textPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.CENTER;
                    textPanel.add(new JLabel("Folgende Spieler haben keine neuen Punkte. wirklich nächste Runde beginnen?"),gbc);
                    JMenuBar playerBar = new JMenuBar();
                    JMenu playerMenu = new JMenu("Spieler");
                    ArrayList<Player> changePlayerList = Game.nextRoundChangedPlayers();
                    for (Player player : changePlayerList) {
                        JMenuItem playerItem = new JMenuItem(player.tisch.number + " " + player.getName()[0] + " " + player.getName()[1]);
                        playerMenu.add(playerItem);
                    }
                    playerMenu.setVisible(true);
                    gbc.gridy = 1;
                    gbc.gridx = 0;
                    playerBar.add(playerMenu);
                    textPanel.add(playerBar, gbc);
                    tischChangeFrame.add(textPanel, BorderLayout.CENTER);
                    confirmButton.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_TAB){
                                denyButton.requestFocus();
                            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                Game.nextRound();
                                tischChangeFrame.dispose();
                            }
                        }
                    });
                    confirmButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Game.nextRound();
                            tischChangeFrame.dispose();
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
                }
            }
        });
        updateMenu.add(nextRoundItem);
        menuBar.add(updateMenu);
        JMenu displayMenu = new JMenu("Anzeige");
        ImageIcon screenIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\screen.png");
        screenIcon.setImage(screenIcon.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH));
        displayMenu.setIcon(screenIcon);
        JMenuItem mainScreen1MenuItem = new JMenuItem("Bidschirm 1");
        JMenuItem mainScreen2MenuItem = new JMenuItem("Bidschirm 2");
        mainScreen2MenuItem.setIcon(screenIcon);
        JMenuItem configScreen1MenuItem = new JMenuItem("Bidschirm 1");
        configScreen1MenuItem.setIcon(screenIcon);
        JMenuItem configScreen2MenuItem = new JMenuItem("Bidschirm 2");
        displayMenu.add(new JLabel("     Tische & Leaderboard"));
        displayMenu.add(mainScreen1MenuItem);
        displayMenu.add(mainScreen2MenuItem);
        displayMenu.addSeparator();
        displayMenu.add(new JLabel("     Konfiguration"));
        displayMenu.add(configScreen1MenuItem);
        displayMenu.add(configScreen2MenuItem);

        mainScreen2MenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mainScreen2MenuItem.setIcon(screenIcon);
                mainFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().getLocation());
                mainScreen1MenuItem.setIcon(null);
            }
        });
        mainScreen1MenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mainScreen1MenuItem.setIcon(screenIcon);
                mainFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].getDefaultConfiguration().getBounds().getLocation());
                mainScreen2MenuItem.setIcon(null);
            }
        });
        configScreen2MenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                configScreen2MenuItem.setIcon(screenIcon);
                configFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds().getLocation());
                configScreen1MenuItem.setIcon(null);
            }
        });
        configScreen1MenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                configScreen1MenuItem.setIcon(screenIcon);
                configFrame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].getDefaultConfiguration().getBounds().getLocation());
                configScreen2MenuItem.setIcon(null);
            }
        });
        menuBar.add(displayMenu);
        configFrame.setJMenuBar(menuBar);

        //ab hier playerConfigpanel
        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel playerInfoPanel = new JPanel(new BorderLayout());
        playerInfoPanel.add(addPanel, BorderLayout.NORTH);
        playerInfoPanel.add(listPanel, BorderLayout.CENTER);
        playerPanel.add(playerInfoPanel);
        playerPanel.add(changePanel);
        playerTab.add(playerPanel, BorderLayout.CENTER);

        //jetzt noch Tisch Panel
        JPanel tischListPanel = new JPanel(new BorderLayout());
        JPanel tischPlayerListPanel = new JPanel(new BorderLayout());
        JPanel tischConfigPanel = new JPanel(new BorderLayout());
        tischTab.add(tischListPanel);
        tischTab.add(tischPlayerListPanel);
        tischTab.add(tischConfigPanel);
        configTischList.setFocusable(false);
        configTischList.setBackground(configPlayerList.getBackground());
        configTischList.setForeground(configPlayerList.getForeground());
        configTischList.setFont(configPlayerList.getFont());
        configTischList.setSelectionBackground(configPlayerList.getSelectionBackground());
        configTischList.setSelectionForeground(configPlayerList.getSelectionForeground());
        JScrollPane tischListScrollPane = new JScrollPane(configTischList);
        tischListScrollPane.setPreferredSize(new Dimension(120,370));
        tischListScrollPane.setFocusable(false);
        configTischList.addListSelectionListener(e -> {
            if(!configTischList.isSelectionEmpty()){
                configTischPlayerListModel.clear();
                if(configTischPlayerListModel.size() == 3){
                    for(int i = 0; i < 4; i ++){
                        configTischPlayerListModel.setElementAt(Game.getTisch(configTischList.getSelectedValue().split(" ")[1]).getPlayerList().get(i).getVorname() + " " + Game.getTisch(configTischList.getSelectedValue().split(" ")[1]).getPlayerList().get(i).getNachname(),i);
                    }
                }else{
                    for(int i = 0; i < 4; i ++){
                        configTischPlayerListModel.addElement(Game.getTisch(configTischList.getSelectedValue().split(" ")[1]).getPlayerList().get(i).getVorname() + " " + Game.getTisch(configTischList.getSelectedValue().split(" ")[1]).getPlayerList().get(i).getNachname());
                    }
                }
            }
        });
        configTischPlayerList.setFocusable(false);
        configTischPlayerList.setBackground(configPlayerList.getBackground());
        configTischPlayerList.setForeground(configPlayerList.getForeground());
        configTischPlayerList.setFont(configPlayerList.getFont());
        configTischPlayerList.setSelectionBackground(configPlayerList.getSelectionBackground());
        configTischPlayerList.setSelectionForeground(configPlayerList.getSelectionForeground());
        configTischPlayerList.addListSelectionListener(e -> {
            if(!configTischPlayerList.isSelectionEmpty()){
                changePlayer = Game.getPlayer(configTischPlayerList.getSelectedValue().split(" ")[0],configTischPlayerList.getSelectedValue().split(" ")[1]);
                assert changePlayer != null;
                changeVornameTextField.setText(changePlayer.getVorname());
                changeNachnameTextField.setText(changePlayer.getNachname());
                pointsTextField.setText(String.valueOf(Objects.requireNonNull(changePlayer).getPoints()));
                roundPointsTextField.setText(String.valueOf((Objects.requireNonNull(changePlayer).getRoundPoints())));
                if(changePlayer.steigtAuf && changePlayer.nextTischSet){
                    if(changePlayer.tisch.number != Game.tischList.size()){
                        nextRoundTischTextField.setText(String.valueOf(changePlayer.getTisch().getNumber() + 1));
                    }else{
                        nextRoundTischTextField.setText("1");
                    }
                }else if(changePlayer.nextTischSet){
                    if(changePlayer.tisch.number != 1){
                        nextRoundTischTextField.setText(String.valueOf(changePlayer.getTisch().getNumber() - 1));
                    }else{
                        nextRoundTischTextField.setText(String.valueOf(Game.tischList.size()));
                    }
                }else{
                    nextRoundTischTextField.setText("N/A");
                }
                if(changePlayer.getTisch() != null){
                    TischLabelNow.setText(changePlayer.getTisch().getName());
                    newTischTextField.setText(changePlayer.getTisch().getName());
                }else{
                    TischLabelNow.setText("N/A");
                }
            }
        });
        tischListPanel.add(tischListScrollPane, BorderLayout.CENTER);
        JScrollPane tischPlayerListScrollPane = new JScrollPane(configTischPlayerList);
        tischPlayerListScrollPane.setPreferredSize(new Dimension(200,90));
        tischPlayerListScrollPane.setFocusable(false);
        tischPlayerListPanel.add(tischPlayerListScrollPane,BorderLayout.CENTER);
        ImageIcon playerIcon = new ImageIcon("C:\\Users\\nnaml\\IdeaProjects\\Schafkopfturnier\\src\\people.png");
        playerIcon.setImage(playerIcon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
        tabbedPane.setIconAt(1,playerIcon);
        tischIcon.setImage(tischIcon.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
        tabbedPane.setIconAt(2,tischIcon);

        tabbedPane.addChangeListener(e -> {
            if(tabbedPane.getSelectedIndex() == 1){
                playerPanel.add(changePanel);
            }else if(tabbedPane.getSelectedIndex() == 2){
                tischConfigPanel.add(changePanel);
            }
        });

        //ab hier server Panel
        clientTable.setBackground(configFrame.getBackground());
        clientTable.setForeground(new Color(186, 186, 186));
        clientTable.setGridColor(new Color(186,186,186));
        clientTable.setRowHeight(40);
        clientTable.getColumnModel().getColumn(0).setPreferredWidth(230);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        clientTable.setFont(new Font(configPlayerList.getFont().getName(),tischPlayerListPanel.getFont().getStyle(),23));
        clientTable.setValueAt("Name",0,0);
        clientTable.setValueAt("IP",0,1);
        clientTable.setValueAt("Status",0,2);
        serverPanel.add(clientTable);
        configFrame.add(new JLabel("©Lötgott & Sesamoel all rights reserved"),BorderLayout.SOUTH);
        configFrame.setLocationRelativeTo(null);
        configFrame.setVisible(true);
        System.out.println(Game.GREEN + "configFrame generated!" + Game.RESET);
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
            System.out.println(Game.GREEN + "Der Name des Spielers " + oldName + " wurde zu " + newVorname + " " + newNachname +" geändert");
        }
        index = configTischPlayerListModel.indexOf(oldName);
        if (index != -1) {
            configTischPlayerListModel.setElementAt(newVorname + " " + newNachname, index);
            oldPlayer.setName(newVorname, newNachname);
            System.out.println(Game.GREEN + "Der Name des Spielers " + oldName + " wurde zu " + newVorname + " " + newNachname +" geändert");
        }
    }
    public static void updateLeaderboard(ArrayList<Player> playerlist){
        leaderboardLabel.setVisible(true);
        for(int i = 0; i < 10; i++){
            if(playerlist.size() > i){
                leaderboardTable.setValueAt(i + 1,i,0);
                leaderboardTable.setValueAt(playerlist.get(i).getName()[0] + " " + playerlist.get(i).getName()[1].charAt(0) + ".",i,1);
                leaderboardTable.setValueAt(playerlist.get(i).getPoints(),i,2);
            }
        }
        System.out.println(Game.YELLOW + "updated the leaderboard" + Game.RESET);
    }
    public static void updateTisch(ArrayList<Tisch> tische){
        for(int i = 0; i < tische.size(); i ++){
            for(int ii = 0; ii < 4; ii ++){
                tischList.get(i).setPlayer(tische.get(i).getPlayerList().get(ii),ii);
            }
        }
    }
    public static void connectWebUser(WebUser webUser){
        boolean notNew = false;
        for(int i = 1; i < clientTable.getRowCount(); i++){
            if(webUser.getIp().equals(clientTable.getValueAt(i, 1))){
                notNew = true;
                clientTable.setValueAt("connected",i,2);
            }
        }
        if(!notNew){
            for(int i = 1; i < clientTable.getRowCount(); i++){
                if(clientTable.getValueAt(i,1) == null){
                    clientTable.setValueAt(webUser.getName(), i,0);
                    clientTable.setValueAt(webUser.getIp(), i,1);
                    clientTable.setValueAt("connected",i,2);
                    break;
                }
            }
        }
    }
    public static void giveWebUserName(String ip, String name){
        for(int i = 1; i < clientTable.getRowCount(); i++){
            if(clientTable.getValueAt(i,1).equals(ip)){
                clientTable.setValueAt(name,i,0);
                break;
            }
        }
    }
    public static void disconnectWebUser(String ip){
        for(int i = 1; i < clientTable.getRowCount(); i++){
            if(clientTable.getValueAt(i,1).equals(ip)){
                clientTable.setValueAt("disconnected",i,2);
            }
        }
    }

    public static void show2EuqalPlayersWarning(Player p1, Player p2){
        JDialog playerEqualityFrame = new JDialog();
        JMenuBar buttonBar = new JMenuBar();
        JButton confirmButton = new JButton("weiter");
        JButton denyButton = new JButton("abbrechen");
        buttonBar.add(confirmButton);
        buttonBar.add(denyButton);
        buttonBar.setBorderPainted(false);
        buttonBar.setBackground(playerEqualityFrame.getBackground());
        buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        playerEqualityFrame.add(buttonBar, BorderLayout.SOUTH);
        playerEqualityFrame.setSize(new Dimension(250, 150));
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        textPanel.add(new JLabel("Folgende Spieler haben gleiche Punkte:"),gbc);
        gbc.gridy = 1;
        textPanel.add(new JLabel("welcher soll aufsteigen?"),gbc);
        JMenuBar playerBar = new JMenuBar();
        JMenu playerMenuUp = new JMenu("Spieler");
        JMenuItem player1Item = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        playerMenuUp.add(player1Item);
        playerMenuUp.add(player2Item);
        playerMenuUp.setVisible(true);
        player1Item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp.setText(player1Item.getText());
            }
        });
        player2Item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp.setText(player2Item.getText());
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Objects.requireNonNull(Game.getPlayer(playerMenuUp.getText().split(" ")[0], playerMenuUp.getText().split(" ")[1])).steigtAuf = true;
                System.out.println(Objects.requireNonNull(Game.getPlayer(playerMenuUp.getText().split(" ")[0], playerMenuUp.getText().split(" ")[1])).getVorname() + " steigt auf");
                Objects.requireNonNull(Game.getPlayer(playerMenuUp.getText().split(" ")[0], playerMenuUp.getText().split(" ")[1])).nextTischSet = true;
                if(Objects.equals(playerMenuUp.getText().split(" ")[0], p1.getVorname()) && Objects.equals(playerMenuUp.getText().split(" ")[1], p1.getNachname())){
                    Objects.requireNonNull(Game.getPlayer(p2.getVorname(), p2.getNachname())).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(p2.getVorname(), p2.getNachname())).steigtAuf = false;
                }else{
                    Objects.requireNonNull(Game.getPlayer(p1.getVorname(), p1.getNachname())).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(p1.getVorname(), p1.getNachname())).steigtAuf = false;
                }
                playerEqualityFrame.dispose();
            }
        });
        denyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Objects.requireNonNull(Game.getPlayer(p1.getVorname(), p1.getNachname())).nextTischSet = false;
                Objects.requireNonNull(Game.getPlayer(p2.getVorname(), p2.getNachname())).nextTischSet = false;
                playerEqualityFrame.dispose();
            }
        });
        gbc.gridy = 2;
        gbc.gridx = 0;
        playerBar.add(playerMenuUp);
        textPanel.add(playerBar, gbc);
        playerEqualityFrame.add(textPanel, BorderLayout.CENTER);
        playerEqualityFrame.setLocationRelativeTo(null);
        playerEqualityFrame.setSize(500,250);
        playerEqualityFrame.setVisible(true);
    }
    public static void show3EqualPlayersDownWarning(Player p1, Player p2, Player p3){
        JDialog playerEqualityFrame = new JDialog();
        JMenuBar buttonBar = new JMenuBar();
        JButton confirmButton = new JButton("weiter");
        JButton denyButton = new JButton("abbrechen");
        buttonBar.add(confirmButton);
        buttonBar.add(denyButton);
        buttonBar.setBorderPainted(false);
        buttonBar.setBackground(playerEqualityFrame.getBackground());
        buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        playerEqualityFrame.add(buttonBar, BorderLayout.SOUTH);
        playerEqualityFrame.setSize(new Dimension(250, 150));
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        textPanel.add(new JLabel("Folgende Spieler haben gleiche Punkte:"),gbc);
        gbc.gridy ++;
        textPanel.add(new JLabel("welche sollen aufsteigen?"),gbc);
        JMenuBar playerBar = new JMenuBar();
        JMenu playerMenuUp1 = new JMenu("Hoch");
        JMenuItem player1Item1 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item1 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item1 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        playerMenuUp1.add(player1Item1);
        playerMenuUp1.add(player2Item1);
        playerMenuUp1.add(player3Item1);
        playerMenuUp1.setVisible(true);
        player1Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player1Item1.getText());
            }
        });
        player2Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player2Item1.getText());
            }
        });
        player3Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player3Item1.getText());
            }
        });
        JMenu playerMenuDown1 = new JMenu("Runter");
        JMenuItem player1Item2 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item2 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item2 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        playerMenuDown1.add(player1Item2);
        playerMenuDown1.add(player2Item2);
        playerMenuDown1.add(player3Item2);
        playerMenuDown1.setVisible(true);
        player1Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player1Item2.getText());
            }
        });
        player2Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player2Item2.getText());
            }
        });
        player3Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player3Item2.getText());
            }
        });
        JMenu playerMenuDown2 = new JMenu("Runter");
        JMenuItem player1Item3 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item3 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item3 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        playerMenuDown2.add(player1Item3);
        playerMenuDown2.add(player2Item3);
        playerMenuDown2.add(player3Item3);
        playerMenuDown2.setVisible(true);
        player1Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player1Item3.getText());
            }
        });
        player2Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player2Item3.getText());
            }
        });
        player3Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player3Item3.getText());
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!Objects.equals(playerMenuUp1.getText(), playerMenuDown1.getText()) && !Objects.equals(playerMenuUp1.getText(), playerMenuDown2.getText()) && !Objects.equals(playerMenuDown1.getText(), playerMenuDown2.getText())){
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp1.getText().split(" ")[1], playerMenuUp1.getText().split(" ")[2])).steigtAuf = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp1.getText().split(" ")[1], playerMenuUp1.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown1.getText().split(" ")[1], playerMenuDown1.getText().split(" ")[2])).steigtAuf = false;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown1.getText().split(" ")[1], playerMenuDown1.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown2.getText().split(" ")[1], playerMenuDown2.getText().split(" ")[2])).steigtAuf = false;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown2.getText().split(" ")[1], playerMenuDown2.getText().split(" ")[2])).nextTischSet = true;
                    playerEqualityFrame.dispose();
                }
            }
        });
        denyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerEqualityFrame.dispose();
            }
        });
        gbc.gridy ++;
        gbc.gridx = 0;
        playerBar.add(playerMenuUp1);
        playerBar.add(playerMenuDown1);
        playerBar.add(playerMenuDown2);
        textPanel.add(playerBar, gbc);
        playerEqualityFrame.add(textPanel, BorderLayout.CENTER);
        playerEqualityFrame.setLocationRelativeTo(null);
        playerEqualityFrame.setSize(500,250);
        playerEqualityFrame.setVisible(true);
    }
    public static void show3EqualPlayersUpWarning(Player p1, Player p2, Player p3){
        JDialog playerEqualityFrame = new JDialog();
        JMenuBar buttonBar = new JMenuBar();
        JButton confirmButton = new JButton("weiter");
        JButton denyButton = new JButton("abbrechen");
        buttonBar.add(confirmButton);
        buttonBar.add(denyButton);
        buttonBar.setBorderPainted(false);
        buttonBar.setBackground(playerEqualityFrame.getBackground());
        buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        playerEqualityFrame.add(buttonBar, BorderLayout.SOUTH);
        playerEqualityFrame.setSize(new Dimension(250, 150));
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        textPanel.add(new JLabel("Folgende Spieler haben gleiche Punkte:"),gbc);
        gbc.gridy ++;
        textPanel.add(new JLabel("welche sollen aufsteigen?"),gbc);
        JMenuBar playerBar = new JMenuBar();
        JMenu playerMenuUp1 = new JMenu("Hoch");
        JMenuItem player1Item1 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item1 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item1 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        playerMenuUp1.add(player1Item1);
        playerMenuUp1.add(player2Item1);
        playerMenuUp1.add(player3Item1);
        playerMenuUp1.setVisible(true);
        player1Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player1Item1.getText());
            }
        });
        player2Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player2Item1.getText());
            }
        });
        player3Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player3Item1.getText());
            }
        });
        JMenu playerMenuUp2 = new JMenu("Hoch");
        JMenuItem player1Item2 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item2 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item2 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        playerMenuUp2.add(player1Item2);
        playerMenuUp2.add(player2Item2);
        playerMenuUp2.add(player3Item2);
        playerMenuUp2.setVisible(true);
        player1Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Hoch: " + player1Item2.getText());
            }
        });
        player2Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Hoch: " + player2Item2.getText());
            }
        });
        player3Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Hoch: " + player3Item2.getText());
            }
        });
        JMenu playerMenuDown = new JMenu("Runter");
        JMenuItem player1Item3 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item3 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item3 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        playerMenuDown.add(player1Item3);
        playerMenuDown.add(player2Item3);
        playerMenuDown.add(player3Item3);
        playerMenuDown.setVisible(true);
        player1Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown.setText("Runter: " + player1Item3.getText());
            }
        });
        player2Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown.setText("Runter: " + player2Item3.getText());
            }
        });
        player3Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown.setText("Runter: " + player3Item3.getText());
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!Objects.equals(playerMenuUp1.getText(), playerMenuUp2.getText()) && !Objects.equals(playerMenuUp1.getText(), playerMenuDown.getText()) && !Objects.equals(playerMenuUp2.getText(), playerMenuDown.getText())){
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp1.getText().split(" ")[1], playerMenuUp1.getText().split(" ")[2])).steigtAuf = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp1.getText().split(" ")[1], playerMenuUp1.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp2.getText().split(" ")[1], playerMenuUp2.getText().split(" ")[2])).steigtAuf = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp2.getText().split(" ")[1], playerMenuUp2.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown.getText().split(" ")[1], playerMenuDown.getText().split(" ")[2])).steigtAuf = false;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown.getText().split(" ")[1], playerMenuDown.getText().split(" ")[2])).nextTischSet = true;
                    playerEqualityFrame.dispose();
                }
            }
        });
        denyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerEqualityFrame.dispose();
            }
        });
        gbc.gridy ++;
        gbc.gridx = 0;
        playerBar.add(playerMenuUp1);
        playerBar.add(playerMenuUp2);
        playerBar.add(playerMenuDown);
        textPanel.add(playerBar, gbc);
        playerEqualityFrame.add(textPanel, BorderLayout.CENTER);
        playerEqualityFrame.setLocationRelativeTo(null);
        playerEqualityFrame.setSize(500,250);
        playerEqualityFrame.setVisible(true);
    }
    public static void show4EqualPlayersUpWarning(Player p1, Player p2, Player p3, Player p4){
        JDialog playerEqualityFrame = new JDialog();
        JMenuBar buttonBar = new JMenuBar();
        JButton confirmButton = new JButton("weiter");
        JButton denyButton = new JButton("abbrechen");
        buttonBar.add(confirmButton);
        buttonBar.add(denyButton);
        buttonBar.setBorderPainted(false);
        buttonBar.setBackground(playerEqualityFrame.getBackground());
        buttonBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        playerEqualityFrame.add(buttonBar, BorderLayout.SOUTH);
        playerEqualityFrame.setSize(new Dimension(250, 150));
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        textPanel.add(new JLabel("Folgende Spieler haben gleiche Punkte:"),gbc);
        gbc.gridy ++;
        textPanel.add(new JLabel("welche sollen aufsteigen?"),gbc);
        JMenuBar playerBar = new JMenuBar();
        JMenu playerMenuUp1 = new JMenu("Hoch");
        JMenuItem player1Item1 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item1 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item1 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        JMenuItem player4Item1 = new JMenuItem(p4.getVorname() + " " + p4.getNachname());
        playerMenuUp1.add(player1Item1);
        playerMenuUp1.add(player2Item1);
        playerMenuUp1.add(player3Item1);
        playerMenuUp1.add(player4Item1);
        playerMenuUp1.setVisible(true);
        player1Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player1Item1.getText());
            }
        });
        player2Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player2Item1.getText());
            }
        });
        player3Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Hoch: " + player3Item1.getText());
            }
        });
        player4Item1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp1.setText("Runter: " + player4Item1.getText());
            }
        });
        JMenu playerMenuUp2 = new JMenu("Hoch");
        JMenuItem player1Item2 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item2 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item2 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        JMenuItem player4Item2 = new JMenuItem(p4.getVorname() + " " + p4.getNachname());
        playerMenuUp2.add(player1Item2);
        playerMenuUp2.add(player2Item2);
        playerMenuUp2.add(player3Item2);
        playerMenuUp2.add(player4Item2);
        playerMenuUp2.setVisible(true);
        player1Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Hoch: " + player1Item2.getText());
            }
        });
        player2Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Hoch: " + player2Item2.getText());
            }
        });
        player3Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Hoch: " + player3Item2.getText());
            }
        });
        player4Item2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuUp2.setText("Runter: " + player4Item2.getText());
            }
        });
        JMenu playerMenuDown1 = new JMenu("Runter");
        JMenuItem player1Item3 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item3 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item3 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        JMenuItem player4Item3 = new JMenuItem(p4.getVorname() + " " + p4.getNachname());
        playerMenuDown1.add(player1Item3);
        playerMenuDown1.add(player2Item3);
        playerMenuDown1.add(player3Item3);
        playerMenuDown1.add(player4Item3);
        playerMenuDown1.setVisible(true);
        player1Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player1Item3.getText());
            }
        });
        player2Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player2Item3.getText());
            }
        });
        player3Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player3Item3.getText());
            }
        });
        player4Item3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown1.setText("Runter: " + player4Item3.getText());
            }
        });
        JMenu playerMenuDown2 = new JMenu("Runter");
        JMenuItem player1Item4 = new JMenuItem(p1.getVorname() + " " + p1.getNachname());
        JMenuItem player2Item4 = new JMenuItem(p2.getVorname() + " " + p2.getNachname());
        JMenuItem player3Item4 = new JMenuItem(p3.getVorname() + " " + p3.getNachname());
        JMenuItem player4Item4 = new JMenuItem(p4.getVorname() + " " + p4.getNachname());
        playerMenuDown2.add(player1Item4);
        playerMenuDown2.add(player2Item4);
        playerMenuDown2.add(player3Item4);
        playerMenuDown2.add(player4Item4);
        playerMenuDown2.setVisible(true);
        player1Item4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player1Item4.getText());
            }
        });
        player2Item4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player2Item4.getText());
            }
        });
        player3Item4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player3Item4.getText());
            }
        });
        player4Item4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerMenuDown2.setText("Runter: " + player4Item4.getText());
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!Objects.equals(playerMenuUp1.getText(), playerMenuUp2.getText()) && !Objects.equals(playerMenuUp1.getText(), playerMenuDown1.getText()) && !Objects.equals(playerMenuUp2.getText(), playerMenuDown1.getText()) && !Objects.equals(playerMenuUp2.getText(), playerMenuDown2.getText()) && !Objects.equals(playerMenuDown1.getText(), playerMenuDown2.getText()) && !Objects.equals(playerMenuUp1.getText(), playerMenuDown2.getText())){
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp1.getText().split(" ")[1], playerMenuUp1.getText().split(" ")[2])).steigtAuf = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp1.getText().split(" ")[1], playerMenuUp1.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp2.getText().split(" ")[1], playerMenuUp2.getText().split(" ")[2])).steigtAuf = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuUp2.getText().split(" ")[1], playerMenuUp2.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown1.getText().split(" ")[1], playerMenuDown1.getText().split(" ")[2])).steigtAuf = false;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown1.getText().split(" ")[1], playerMenuDown1.getText().split(" ")[2])).nextTischSet = true;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown2.getText().split(" ")[1], playerMenuDown2.getText().split(" ")[2])).steigtAuf = false;
                    Objects.requireNonNull(Game.getPlayer(playerMenuDown2.getText().split(" ")[1], playerMenuDown2.getText().split(" ")[2])).nextTischSet = true;
                    playerEqualityFrame.dispose();
                }
            }
        });
        denyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                playerEqualityFrame.dispose();
            }
        });
        gbc.gridy ++;
        gbc.gridx = 0;
        playerBar.add(playerMenuUp1);
        playerBar.add(playerMenuUp2);
        playerBar.add(playerMenuDown1);
        playerBar.add(playerMenuDown2);
        textPanel.add(playerBar, gbc);
        playerEqualityFrame.add(textPanel, BorderLayout.CENTER);
        playerEqualityFrame.setLocationRelativeTo(null);
        playerEqualityFrame.setSize(500,250);
        playerEqualityFrame.setVisible(true);
    }
}