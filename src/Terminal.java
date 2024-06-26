import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Terminal{
    private Scanner scanner;
    public static Game game = new Game();
    public Terminal() {
        this.scanner = new Scanner(System.in);
    }

    public void checkForInput() {
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            run(userInput);
        }
    }

    public void run(String userInput){
        String[] input1 = userInput.split(" "); //aufspalten der wörter
        ArrayList<String> input = new ArrayList<>(Arrays.asList(input1));//machen zu einer arraylist
        switch(input.get(0)){
            case "info":
                System.out.println("addplayer <name> \n" +
                                    "updateleaderboard <> \n" +
                                    "updatetisch <> \n" +
                                    "spreadplayers <> \n" +
                                    "swapplayers <player1> <player2>");
                break;
            case "addplayer":
                input.remove(0);
                if(!input.isEmpty()){
                    String vorname = input.get(0);
                    input.remove(0);
                    if(!input.isEmpty()){
                        String nachname = input.get(0);
                        game.addPlayer(new Player(vorname,nachname));
                    }
                }
                break;
            case "updateleaderboard":
                System.out.println(" playerList vor Tausch" + Game.playerList.size());
                Game.spreadPlayers();
                System.out.println(" Tisch list nach atsuch" + Game.tischList.size());
                break;

            case "updatetisch":
                Game.updateTisch();
                break;

            case "spreadplayers":
                Game.spreadPlayers();
                break;

            case "swapplayers":
                input.remove(0);
                if(Game.getPlayer( input.get(0) , input.get(1))==null || Game.getPlayer( input.get(2) , input.get(3))==null){
                    System.out.println("Ein Name wird nicht gefunden. Bitte auf Rechtschreibung achten!");
                }else{
                    Game.spielertausch(Objects.requireNonNull(Game.getPlayer(input.get(0), input.get(1))), Objects.requireNonNull(Game.getPlayer(input.get(2), input.get(3))));
                }
                break;
            case "addplayerpoints":
                input.remove(0);
                if(!input.isEmpty()){
                    String vorname = input.get(0);
                    String nachname;
                    input.remove(0);
                    if(!input.isEmpty()){
                        nachname = input.get(0);
                        input.remove(0);
                        if(Game.getPlayer(vorname,nachname) != null){
                            if(!input.get(0).isEmpty()){
                                Game.getPlayer(vorname,nachname).addRoundPoints(Integer.parseInt(input.get(0)));
                            }else{
                                System.out.println(Game.RED + "Bitte Punktzahl eingeben!");
                            }
                        }else{
                            System.out.println(Game.RED + "Spieler konnte nicht gefunden werden! Bitte auch Rechtschreibung achten." + Game.RESET);
                        }
                    }
                }
            default:
                System.out.println("unbekannter befehl! bitte auf Rechtschreibung achten");
        }
    }
}