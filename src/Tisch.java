import java.util.ArrayList;
import java.util.Comparator;

public class Tisch{
    int number;
    ArrayList<Player> playerList = new ArrayList<>();
    public Tisch(int newNumber) {
        number = newNumber;
    }
    public Tisch(Player player1,Player player2,Player player3,Player player4){
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
    }
    public void sortPlayers(){
        playerList.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getRoundPoints() < o2.getRoundPoints()) {
                    return -1;
                } else if (o1.getRoundPoints() > o2.getRoundPoints()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        if(playerList.get(1).getRoundPoints() == playerList.get(2).getRoundPoints()  && playerList.get(3).getRoundPoints() == playerList.get(2).getRoundPoints() && playerList.get(0).getRoundPoints() == playerList.get(2).getRoundPoints()) {
            if(playerList.get(0).nextTischSet){
                if(playerList.get(1).nextTischSet){
                    if(playerList.get(2).nextTischSet){
                        if(playerList.get(3).nextTischSet){
                            int upPlayerAmount = 0;
                            int downPlayerAmount = 0;
                            for(int i = 0; i < 4; i++){
                                if(playerList.get(i).steigtAuf){
                                    upPlayerAmount ++;
                                    System.out.println("up");
                                }else{
                                    downPlayerAmount ++;
                                    System.out.println("down");
                                }
                            }
                            if(upPlayerAmount - downPlayerAmount == 0){
                                playerList.sort(new Comparator<Player>() {
                                    @Override
                                    public int compare(Player o1, Player o2) {
                                        if(o1.steigtAuf && o2.steigtAuf){
                                            return 0;
                                        }else if(o1.steigtAuf && !o2.steigtAuf){
                                            return 1;
                                        }else {
                                            return -1;
                                        }
                                    }
                                });
                            }else{
                                System.out.println(Game.RED + "Achtung! an Tisch " + number + " steigen " + upPlayerAmount + " auf und " + downPlayerAmount + " ab. Bitte beheben!" + Game.RESET);
                                Gui.show4EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
                            }
                        }else{
                            int upPlayerAmount = 0;
                            int downPlayerAmount = 0;
                            for(int i = 0; i < 3; i++){
                                if(playerList.get(i).steigtAuf){
                                    upPlayerAmount ++;
                                    System.out.println("up");
                                }else{
                                    downPlayerAmount ++;
                                    System.out.println("down");
                                }
                            }
                            if(upPlayerAmount <= 2 && downPlayerAmount <= 2){
                                if(upPlayerAmount == 2){
                                    playerList.get(3).steigtAuf = false;
                                    playerList.get(3).nextTischSet = true;
                                }else {
                                    playerList.get(3).steigtAuf = true;
                                    playerList.get(3).nextTischSet = true;
                                }
                            }else if(upPlayerAmount <= 2){
                                System.out.println(Game.RED + "Achtung! an Tisch " + number + " steigen " + upPlayerAmount + " auf. Bitte beheben!" + Game.RESET);
                                Gui.show4EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
                            }else{
                                System.out.println(Game.RED + "Achtung! an Tisch " + number + " steigen " + downPlayerAmount + " ab. Bitte beheben!" + Game.RESET);
                                Gui.show4EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
                            }
                        }
                    }else{
                        int upPlayerAmount = 0;
                        int downPlayerAmount = 0;
                        for(int i = 0; i < 2; i++){
                            if(playerList.get(i).steigtAuf){
                                upPlayerAmount ++;
                                System.out.println("up");
                            }else{
                                downPlayerAmount ++;
                                System.out.println("down");
                            }
                        }
                        if(upPlayerAmount == 2){
                            playerList.get(2).steigtAuf = false;
                            playerList.get(3).steigtAuf = false;
                            playerList.get(2).nextTischSet = true;
                            playerList.get(3).nextTischSet = true;
                        }else if(downPlayerAmount == 2){
                            playerList.get(2).steigtAuf = true;
                            playerList.get(3).steigtAuf = true;
                            playerList.get(2).nextTischSet = true;
                            playerList.get(3).nextTischSet = true;
                        }else{
                            System.out.println(Game.RED + "Achtung: zwei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                            Gui.show2EuqalPlayersWarning(playerList.get(2),playerList.get(3));
                        }
                    }
                }else{
                    if(playerList.get(0).steigtAuf){
                        System.out.println(Game.RED + "Achtung: drei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show3EqualPlayersDownWarning(playerList.get(1),playerList.get(2),playerList.get(3));
                    }else if(!playerList.get(0).steigtAuf){
                        System.out.println(Game.RED + "Achtung: drei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show3EqualPlayersUpWarning(playerList.get(1),playerList.get(2),playerList.get(3));
                    }
                }
            }else if(playerList.get(1).nextTischSet){
                if(playerList.get(2).nextTischSet){
                    if(playerList.get(3).nextTischSet){
                        int upPlayerAmount = 0;
                        int downPlayerAmount = 0;
                        for(int i = 1; i < 4; i++){
                            if(playerList.get(i).steigtAuf){
                                upPlayerAmount ++;
                                System.out.println("up");
                            }else{
                                downPlayerAmount ++;
                                System.out.println("down");
                            }
                        }
                        if(upPlayerAmount <= 2 && downPlayerAmount <= 2){
                            if(upPlayerAmount == 2){
                                playerList.get(0).steigtAuf = false;
                                playerList.get(0).nextTischSet = true;
                            }else {
                                playerList.get(0).steigtAuf = true;
                                playerList.get(0).nextTischSet = true;
                            }
                        }else if(upPlayerAmount <= 2){
                            System.out.println(Game.RED + "Achtung! an Tisch " + number + " steigen " + upPlayerAmount + " auf. Bitte beheben!" + Game.RESET);
                            Gui.show4EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
                        }else{
                            System.out.println(Game.RED + "Achtung! an Tisch " + number + " steigen " + downPlayerAmount + " ab. Bitte beheben!" + Game.RESET);
                            Gui.show4EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
                        }
                    }else{
                        int upPlayerAmount = 0;
                        int downPlayerAmount = 0;
                        for(int i = 1; i < 3; i++){
                            if(playerList.get(i).steigtAuf){
                                upPlayerAmount ++;
                                System.out.println("up");
                            }else{
                                downPlayerAmount ++;
                                System.out.println("down");
                            }
                        }
                        if(upPlayerAmount == 2){
                            playerList.get(0).steigtAuf = false;
                            playerList.get(3).steigtAuf = false;
                            playerList.get(0).nextTischSet = true;
                            playerList.get(3).nextTischSet = true;
                        }else if(downPlayerAmount == 2){
                            playerList.get(0).steigtAuf = true;
                            playerList.get(3).steigtAuf = true;
                            playerList.get(0).nextTischSet = true;
                            playerList.get(3).nextTischSet = true;
                        }else{
                            System.out.println(Game.RED + "Achtung: zwei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                            Gui.show2EuqalPlayersWarning(playerList.get(0),playerList.get(3));
                        }
                    }
                }else{
                    if(playerList.get(1).steigtAuf){
                        System.out.println(Game.RED + "Achtung: drei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show3EqualPlayersDownWarning(playerList.get(0),playerList.get(2),playerList.get(3));
                    }else if(!playerList.get(1).steigtAuf){
                        System.out.println(Game.RED + "Achtung: drei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show3EqualPlayersUpWarning(playerList.get(0),playerList.get(2),playerList.get(3));
                    }
                }
            }else if(playerList.get(2).nextTischSet){
                if(playerList.get(3).nextTischSet){
                    int upPlayerAmount = 0;
                    int downPlayerAmount = 0;
                    for(int i = 2; i < 4; i++){
                        if(playerList.get(i).steigtAuf){
                            upPlayerAmount ++;
                            System.out.println("up");
                        }else{
                            downPlayerAmount ++;
                            System.out.println("down");
                        }
                    }
                    if(upPlayerAmount == 2){
                        playerList.get(0).steigtAuf = false;
                        playerList.get(1).steigtAuf = false;
                        playerList.get(0).nextTischSet = true;
                        playerList.get(1).nextTischSet = true;
                    }else if(downPlayerAmount == 2){
                        playerList.get(0).steigtAuf = true;
                        playerList.get(1).steigtAuf = true;
                        playerList.get(0).nextTischSet = true;
                        playerList.get(1).nextTischSet = true;
                    }else{
                        System.out.println(Game.RED + "Achtung: zwei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show2EuqalPlayersWarning(playerList.get(0),playerList.get(1));
                    }
                }else{
                    if(playerList.get(3).steigtAuf){
                        System.out.println(Game.RED + "Achtung: drei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show3EqualPlayersDownWarning(playerList.get(0),playerList.get(1),playerList.get(2));
                    }else if(!playerList.get(3).steigtAuf){
                        System.out.println(Game.RED + "Achtung: drei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
                        Gui.show3EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2));
                    }
                }
            }
            if(playerList.get(3).nextTischSet){

            }
            if(!playerList.get(0).nextTischSet && !playerList.get(1).nextTischSet && !playerList.get(2).nextTischSet && !playerList.get(3).nextTischSet){
                playerList.get(0).nextTischSet = false;
                playerList.get(1).nextTischSet = false;
                playerList.get(2).nextTischSet = false;
                playerList.get(3).nextTischSet = false;
                Gui.show4EqualPlayersUpWarning(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
            }
        }else if(playerList.get(1).getRoundPoints() == playerList.get(2).getRoundPoints()  && playerList.get(0).getRoundPoints() == playerList.get(2).getRoundPoints()) {
            playerList.get(0).nextTischSet = false;
            playerList.get(1).nextTischSet = false;
            playerList.get(2).nextTischSet = false;
            playerList.get(3).steigtAuf = true;
            playerList.get(3).nextTischSet = true;
            Gui.show3EqualPlayersDownWarning(playerList.get(0),playerList.get(1),playerList.get(2));
        }else if(playerList.get(1).getRoundPoints() == playerList.get(2).getRoundPoints()  && playerList.get(3).getRoundPoints() == playerList.get(2).getRoundPoints()){
            playerList.get(1).nextTischSet = false;
            playerList.get(2).nextTischSet = false;
            playerList.get(3).nextTischSet = false;
            playerList.get(0).steigtAuf = false;
            playerList.get(0).nextTischSet = true;
            Gui.show3EqualPlayersUpWarning(playerList.get(1),playerList.get(2),playerList.get(3));
        }else if(playerList.get(1).getRoundPoints() == playerList.get(2).getRoundPoints()  && !playerList.get(1).nextTischSet && !playerList.get(2).nextTischSet){
            System.out.println(Game.RED + "Achtung: zwei Spieler an Tisch " + number + " haben gleich viele Punkte" + Game.RESET);
            Gui.show2EuqalPlayersWarning(playerList.get(1),playerList.get(2));
            playerList.get(1).nextTischSet = false;
            playerList.get(2).nextTischSet = false;
            playerList.get(0).setSteigtAuf(false);
            playerList.get(0).nextTischSet = true;
            playerList.get(3).setSteigtAuf(true);
            playerList.get(3).nextTischSet = true;
        }else if(playerList.get(1).nextTischSet){
            playerList.get(2).steigtAuf = !playerList.get(1).steigtAuf;
        }else if(playerList.get(2).nextTischSet){
            playerList.get(1).steigtAuf = !playerList.get(2).steigtAuf;
        }else{
            playerList.get(0).setSteigtAuf(false);
            playerList.get(0).nextTischSet = true;
            playerList.get(1).setSteigtAuf(false);
            playerList.get(1).nextTischSet = true;
            playerList.get(2).setSteigtAuf(true);
            playerList.get(2).nextTischSet = true;
            playerList.get(3).setSteigtAuf(true);
            playerList.get(3).nextTischSet = true;
        }
        System.out.print(playerList.get(0).getVorname());
        System.out.println(" " + playerList.get(0).nextTischSet);
        System.out.print(playerList.get(1).getVorname());
        System.out.println(" " + playerList.get(1).nextTischSet);
        System.out.print(playerList.get(2).getVorname());
        System.out.println(" " + playerList.get(2).nextTischSet);
        System.out.print(playerList.get(3).getVorname());
        System.out.println(" " + playerList.get(3).nextTischSet);
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
