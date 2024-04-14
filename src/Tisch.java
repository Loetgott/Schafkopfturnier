import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tisch{
    int number;
    ArrayList<Player> playerList = new ArrayList<>();
    public Tisch() {

    }
    public Tisch(Player player1,Player player2,Player player3,Player player4){

    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
    public String getName() {
        return String.valueOf(this.number);
    }
    public int getNumber(){
        return this.number;
    }
}
