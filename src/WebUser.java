import java.util.Objects;

public class WebUser {
    String ip;
    String Name;
    boolean connected;
    public int tempTischNumber;
    public Player tempPlayer1;
    public Player tempPlayer2;
    public Player tempPlayer3;
    public Player tempPlayer4;
    public WebUser(String nIp){
        ip = nIp;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public void addPlayerPoints(){
        System.out.println("punkte verarbeiten...");
        for(int i = 0; i < Game.tischList.get(tempTischNumber - 1).playerList.size(); i++){
            if(Objects.equals(Game.tischList.get(tempTischNumber - 1).playerList.get(i).getVorname(), tempPlayer1.getVorname()) && (Game.tischList.get(tempTischNumber - 1).playerList.get(i).getNachname().charAt(0) + ".").equals(tempPlayer1.getNachname())){
                //System.out.println("spieler1 gefunden");
                Game.tischList.get(tempTischNumber -1).playerList.get(i).addRoundPoints(tempPlayer1.points);
            }
        }
        for(int i = 0; i < Game.tischList.get(tempTischNumber - 1).playerList.size(); i++){
            if(Objects.equals(Game.tischList.get(tempTischNumber - 1).playerList.get(i).getVorname(), tempPlayer2.getVorname()) && (Game.tischList.get(tempTischNumber - 1).playerList.get(i).getNachname().charAt(0) + ".").equals(tempPlayer2.getNachname())){
                //System.out.println("spieler2 gefunden");
                Game.tischList.get(tempTischNumber -1).playerList.get(i).addRoundPoints(tempPlayer2.points);
            }
        }
        for(int i = 0; i < Game.tischList.get(tempTischNumber - 1).playerList.size(); i++){
            if(Objects.equals(Game.tischList.get(tempTischNumber - 1).playerList.get(i).getVorname(), tempPlayer3.getVorname()) && (Game.tischList.get(tempTischNumber - 1).playerList.get(i).getNachname().charAt(0) + ".").equals(tempPlayer3.getNachname())){
                //System.out.println("spieler3 gefunden");
                Game.tischList.get(tempTischNumber -1).playerList.get(i).addRoundPoints(tempPlayer3.points);
            }
        }
        for(int i = 0; i < Game.tischList.get(tempTischNumber - 1).playerList.size(); i++){
            if(Objects.equals(Game.tischList.get(tempTischNumber - 1).playerList.get(i).getVorname(), tempPlayer4.getVorname()) && (Game.tischList.get(tempTischNumber - 1).playerList.get(i).getNachname().charAt(0) + ".").equals(tempPlayer4.getNachname())){
                //System.out.println("spieler4 gefunden");
                Game.tischList.get(tempTischNumber -1).playerList.get(i).addRoundPoints(tempPlayer4.points);
            }
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
