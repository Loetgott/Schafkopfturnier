import java.util.ArrayList;
import java.util.Objects;

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
    public static Player getPlayer(String vorname, String name){
        for(Player player : playerList){
            if(Objects.equals(player.getName(), vorname + " " + name)){
                return player;
            }
        }
        return null;
    }
}
