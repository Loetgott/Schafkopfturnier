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
    public static final String WHITE = "\u001B[37m";
    public Game() {

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
    public static void setPlayerName(Player player, String vorname, String nachname){
        Gui.setPlayerName(player,vorname, nachname);
        player.setName(vorname,nachname);
    }
    public static void setPlayerPoints(Player player, int points){
        player.setPoints(points);
    }
    public static void addPlayerPoints(Player player, int points){
        player.setPoints(player.getPoints() + points);
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
        Gui.updateTisch();
    }

    public static void spielerZuordnen(){
        if(playerList.size() % 4 != 0){ //Überprüfe, ob genug Spieler vorhanden sind und Platzhalter einfügen
            for(int i = 0; i < playerList.size() % 4 ; i++){
                addPlayer("Platzhalter", String.valueOf(i));
            }
            System.out.println(RED + "Nicht genug Spieler! " + playerList.size() % 4 + "Platzhalter hinzugefügt. Bitte umbenennen!" + RESET);
        }
        Collections.shuffle(playerList);//playerList mischen
        for(int i = 0; i < playerList.size() / 4 ; i++){//Tische hinzufügen
            Tisch nTisch = new Tisch(i);
            for(int ii = 0; ii < 4 ; ii++){
                nTisch.playerList.add(playerList.get(i * 4 + ii));
                playerList.get( i * 4 + ii ).setTisch(nTisch);
            }
            tischList.add(nTisch);
        }


    }

    public static void spielertausch (Player player1 , Player player2){
        System.out.println("getauscht!");
        Tisch tisch1 = player1.getTisch();      //Tische der Spieler zwischenspeichern
        Tisch tisch2 = player2.getTisch();

        tisch1.playerList.remove(player1);      //Spieler aus Ursprungstischen entfernen
        tisch2.playerList.remove(player2);
        tisch1.playerList.add(player2);         //Spieler in neue Tische hinzufügen
        tisch2.playerList.add(player1);

        player1.setTisch(tisch2);               //Attribut Tisch in Spieler ändern
        player2.setTisch(tisch1);

    }

    public static Player sucheSpieler(String vorname, String nachname) {
        for (Player player : playerList) {
            if (player.getVorname().equals(vorname) && player.getNachname().equals(nachname)) {
                return player; // Spieler gefunden
            }
        }
        return null; // Spieler nicht gefunden
    }


}
