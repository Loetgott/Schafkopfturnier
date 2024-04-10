import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tisch extends JComponent{
    Dimension Size = new Dimension(75,75);
    ArrayList<Player> playerList = new ArrayList<>();
    public Tisch() {

    }
    public Tisch(Player player1,Player player2,Player player3,Player player4){

    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(new Color(93, 42, 0));
        g.fillRect(0,0, (int) getSize().getWidth(), (int) getSize().getHeight());
    }
    public void setSize(int nSize){

    }
    public Dimension getSize(){
        return Size;
    }

}
