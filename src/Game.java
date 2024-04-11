import java.util.ArrayList;

public class Game {
    public static ArrayList<Player> playerList = new ArrayList<>();
    public Game() {

    }
    public static void addPlayer(Player nPlayer){
        playerList.add(nPlayer);
        Gui.addPlayerToList(nPlayer);
    }

    public static void addPlayer(String name, String vorname) {
        Player nPlayer = new Player(name,vorname);
        playerList.add(nPlayer);
        Gui.addPlayerToList(nPlayer);
    }
}
