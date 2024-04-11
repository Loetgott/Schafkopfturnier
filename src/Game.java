import java.util.ArrayList;

public class Game {
    ArrayList<Player> playerList = new ArrayList<>();
    public Game() {

    }
    public void addPlayer(Player nPlayer){
        playerList.add(nPlayer);
    }
}
