import java.util.ArrayList;
import java.util.Objects;

public class Game {
    public static ArrayList<Player> playerList = new ArrayList<>();
    public static ArrayList<Tisch> tischList = new ArrayList<>();
    public Game() {

    }
    public static void addPlayer(Player nPlayer){
        playerList.add(nPlayer);
        Gui.addPlayerToList(nPlayer);
    }

    public static void addPlayer(String vorname, String nachname) {
        Player nPlayer = new Player(vorname,nachname);
        playerList.add(nPlayer);
        Gui.addPlayerToList(nPlayer);
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
