import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;

public class Game {
    public static ArrayList<Player> playerList = new ArrayList<>();
    public static ArrayList<Tisch> tischList = new ArrayList<>();
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static XMLMaker xmlMaker = new XMLMaker();
    public static int round;
    public Game() {
        xmlMaker = new XMLMaker();
    }
    public static void addPlayer(Player nPlayer){
        playerList.add(nPlayer);
        Gui.addPlayerToList(nPlayer);
        System.out.println("Spieler mit dem Namen " + nPlayer.getName()[0] + " " + nPlayer.getName()[1] + " hinzugefügt");
    }

    public static void addPlayer(String vorname, String nachname) {
        Player nPlayer = new Player(vorname,nachname);
        playerList.add(nPlayer);
        Gui.addPlayerToList(nPlayer);
        System.out.println(GREEN + "Spieler mit dem Namen " + vorname + " " + nachname + " hinzugefügt" + RESET);
    }
    public static Player getPlayer(String vorname, String nachname){
        for(Player player : playerList){
            if(Objects.equals(player.getName()[0], vorname) && Objects.equals(player.getName()[1], nachname)){
                return player;
            }
        }
        return null;
    }
    public static Tisch getTisch(String name){
        for(Tisch tisch : tischList){
            if(Objects.equals(tisch.getName(), name)){
                return tisch;
            }
        }
        return null;
    }

    public static void setPlayerName(Player player, String vorname, String nachname){
        Gui.setPlayerName(player,vorname, nachname);
        player.setName(vorname,nachname);
    }
    public static void setPlayerPoints(Player player, int points){
        player.setPoints(points);
    }
    public static void addPlayerPoints(Player player, int points){
        player.addRoundPoints(points);
    }
    public static void updateLeaderboard(){
        int n = playerList.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (playerList.get(j).getPoints() < playerList.get(j + 1).getPoints()){
                    // swap temp and arr[i]
                    Player temp = playerList.get(j);
                    playerList.set(j, playerList.get(j + 1));
                    playerList.set(j + 1, temp);
                }

        Gui.updateLeaderboard(playerList);
    }

    public static void updateTisch(){
        Gui.updateTisch(tischList);
        System.out.println(GREEN + "Tische wurden geupdated!" + RESET);
    }

    public static void spielerZuordnen(){
        tischList.clear();
        if(playerList.size() % 4 != 0){ //Überprüfe, ob genug Spieler vorhanden sind und Platzhalter einfügen
            int newPlayerCount = 4 - (playerList.size() % 4);
            for(int i = 0; i < playerList.size() % 4 ; i++){
                addPlayer("Platzhalter", String.valueOf(i));
            }
            System.out.println(RED + "Nicht genug Spieler! " + newPlayerCount + " Platzhalter hinzugefügt. Bitte umbenennen!" + RESET);
        }
        Collections.shuffle(playerList);//playerList mischen
        for(int i = 0; i < playerList.size() / 4 ; i++){//Tische hinzufügen
            Tisch nTisch = new Tisch(i + 1);
            for(int ii = 0; ii < 4 ; ii++){
                nTisch.playerList.add(playerList.get(i * 4 + ii));
                playerList.get(i * 4 + ii).setTisch(nTisch);
            }
            tischList.add(nTisch);
        }


    }
    public static void saveBackup(String path){
        xmlMaker.saveBackup(tischList,path);
    }
    public static void importSavegame(String path){
        xmlMaker.importBackup(path);
    }

    public static void spielertausch (Player player1 , Player player2){
        Tisch tisch1 = player1.getTisch();
        Tisch tisch2 = player2.getTisch();
        int player1Position = tisch1.playerList.indexOf(player1);
        int player2Position = tisch2.playerList.indexOf(player2);
        tisch1.playerList.set(player1Position, player2);
        tisch2.playerList.set(player2Position, player1);
        player1.setTisch(tisch2);
        player2.setTisch(tisch1);
        for(int i = 0; i < Gui.tischList.size(); i ++){
            Gui.tischList.get(i).repaint();
        }
        System.out.println(GREEN + player1.getName()[0] + " " + player1.getName()[1] + player2.getName()[0] + " " + player2.getName()[1] + RESET);
    }

    public static Player sucheSpieler(String vorname, String nachname) {
        for (Player player : playerList) {
            if (player.getVorname().equals(vorname) && player.getNachname().equals(nachname)) {
                return player;
            }
        }
        return null;
    }
    public static ArrayList<Player> nextRoundChangedPlayers(){
        ArrayList<Player> notChangedPlayersList = new ArrayList<>();
        for(Player player : playerList){
            if(player.hasRoundPoints == true){
                player.nextRound();
            }else{
                notChangedPlayersList.add(player);
                System.out.println(player.getName());
            }
        }
        return notChangedPlayersList;
    }

    public static void setPointsTisch(Tisch tisch, int pP0, int pP1, int pP2, int pP3){
        if( pP0 + pP1 + pP2 + pP3 != 0){
            System.out.println("Punktezahl geht nicht auf! Bitte überprüfen");
        }else{
            addPlayerPoints(tisch.playerList.get(0),pP0);           //Punkte einfügen und playerlist nach Punkten sortieren
            addPlayerPoints(tisch.playerList.get(1),pP1);
            addPlayerPoints(tisch.playerList.get(2),pP2);
            addPlayerPoints(tisch.playerList.get(3),pP3);

            Collections.sort(tisch.playerList);

            if(tisch.playerList.get(1).getRoundPoints() == tisch.playerList.get(2).getRoundPoints()){
                System.out.println(RED + "Punktegleichheit! Bitte manuell eintragen, wer aufsteigt." + RESET);
            }else{
                tisch.playerList.get(0).setSteigtAuf(true);
                tisch.playerList.get(1).setSteigtAuf(true);
                tisch.playerList.get(2).setSteigtAuf(false);
                tisch.playerList.get(3).setSteigtAuf(false);
            }
        }
    }

    public static void nextRound() {
        xmlMaker.nextRound();
        round ++;
    }
}
