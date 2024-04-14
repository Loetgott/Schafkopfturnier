import java.util.ArrayList;
import java.util.Objects;

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
}
